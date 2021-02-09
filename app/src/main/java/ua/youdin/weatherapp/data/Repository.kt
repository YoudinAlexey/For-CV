package ua.youdin.weatherapp.data

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period
import ua.youdin.weatherapp.data.sourse.localSqlApi.TasksLocalSqlDataSourse
import ua.youdin.weatherapp.data.sourse.localSqlApi.asCurentWeather
import ua.youdin.weatherapp.data.sourse.localSqlApi.asDailyWeather
import ua.youdin.weatherapp.data.sourse.localSqlApi.asHourlyWeather
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CurentWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB
import ua.youdin.weatherapp.data.sourse.openWeatherApi.TasksOpenWeatherSourse
import ua.youdin.weatherapp.data.sourse.openWeatherApi.geocodingdata.GeocodingItem
import ua.youdin.weatherapp.servise.Result.Success


class Repository(
    private val localSqlDataSourse: TasksLocalSqlDataSourse,
    private val openWeatherSourse: TasksOpenWeatherSourse,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    TasksForRepository {

    private fun temperatureToString(temperature_units: Boolean) =
        if (temperature_units) "metric" else "imperial"

    private fun temperatureToBoolean(temperature: String) =
        when (temperature) {
            "metric" -> true
            else -> false
        }


    override suspend fun recordSelectedCity(
        city: Pair<String, LatLng>,
        temperature_units: Boolean,
        interface_language: String
    ) {
        val weather = openWeatherSourse.loadWeather(
            city, temperatureToString(temperature_units), interface_language
        )
        if (weather is Success) {
            val id = localSqlDataSourse.recordSelectedCity(
                city = city,
                temperatureToString(temperature_units),
                interface_language
            )
            val curentWeather: CurentWeatherDB = weather.data.asCurentWeather(id)
            val hurlyWeather: List<HourlyWeatherDB> = weather.data.asHourlyWeather(id)
            val dailytWeather: List<DailyWeatherDB> = weather.data.asDailyWeather(id)
            localSqlDataSourse.saveWeatherForCityById(
                curentWeather,
                hurlyWeather,
                dailytWeather
            )
        }
    }

    override suspend fun updateSelectedCity(
        city: CitiesUserSelectedDB,
        temperature_units: Boolean,
        interface_language: String
    ) {
        localSqlDataSourse.updateSelectedCity(city = city)
        val _city: Pair<String, LatLng> = city.name to LatLng(city.latitude, city.longitude)
        val weather =
            openWeatherSourse.loadWeather(
                _city,
                temperatureToString(temperature_units),
                interface_language
            )
        // Log.d("Tag", weather.toString())
        if (weather is Success) {
            val curentWeather: CurentWeatherDB = weather.data.asCurentWeather(city.city_id.toLong())
            val hurlyWeather: List<HourlyWeatherDB> =
                weather.data.asHourlyWeather(city.city_id.toLong())
            val dailytWeather: List<DailyWeatherDB> =
                weather.data.asDailyWeather(city.city_id.toLong())
            localSqlDataSourse.updateWeatherForCityById(
                curentWeather,
                hurlyWeather,
                dailytWeather
            )
        }
    }

    override suspend fun selectionSelectedCity(): List<CitiesUserSelectedDB> =
        localSqlDataSourse.selectionSelectedCity()


    override suspend fun deleteSelectedCity_ByID(cityId: Long) {
        localSqlDataSourse.deleteSelectedCityByid(cityId = cityId)
    }

    override suspend fun refreshAll(
        temperature_units: Boolean,
        interface_language: String
    ) {
        val listCities = localSqlDataSourse.getAllCities()
        if (listCities.isNotEmpty()) listCities.forEach { city ->
            updateSelectedCity(city, temperature_units, interface_language)
        }
    }

    override suspend fun refreshOne(city: String) {
        val listCities = localSqlDataSourse.getAllCities()
        val cityId =
            listCities.first { c -> c.name == city }.city_id
        val isUpdateAllowed = isUpdateAllowed(cityId)
        if (isUpdateAllowed) {
            if (listCities.isNotEmpty()) listCities.forEach { c ->
                updateSelectedCity(
                    c,
                    temperatureToBoolean(c.temperature),
                    c.interface_language
                )
            }
        }
    }

    private suspend fun isUpdateAllowed(cityId: Int): Boolean {
        val hourlyList = getListHourlyWeatherDB_ByCityID(cityId)
        val timeZone = hourlyList.first().timezone
        val dbTimePlusPeriod = (DateTime(
            hourlyList.first().dt * 1000L,
            DateTimeZone.forID(timeZone)
        ) + Period.hours(2))
        val curentTime = DateTime(DateTimeZone.forID(timeZone))
        return curentTime.millis > dbTimePlusPeriod.millis
    }


    override suspend fun getListDailyWeatherDB_ByCityID(cityId: Int): List<DailyWeatherDB> =
        localSqlDataSourse.getListDailyWeatherDBByCityID(cityId)

    override suspend fun getListHourlyWeatherDB_ByCityID(cityId: Int): List<HourlyWeatherDB> =
        localSqlDataSourse.getListHourlyWeatherDBByCityID(cityId)


    override suspend fun getCoordinatesbyGeocodingCities(city: String): List<GeocodingItem> {
        val geocodingCoordinates = openWeatherSourse.directGeocoding(city = city)
        return if (geocodingCoordinates is Success) {
            geocodingCoordinates.data
        } else emptyList()
    }

    override suspend fun getCitiesbyGeocodingCoordinates(
        lat: Double,
        lon: Double
    ): List<GeocodingItem> {
        val geocodingCities = openWeatherSourse.reverseGeocoding(lat = lat, lon = lon)
        return if (geocodingCities is Success) {
            geocodingCities.data
        } else emptyList()
    }

    override suspend fun getTransformCoordinatesbyGeocodingCities(
        city: String,
        localLang: String
    ): Map<String, LatLng> =
        openWeatherSourse.transformGeocodingToLocalLangGeocoding(
            listCities = getCoordinatesbyGeocodingCities(city),
            localLang = localLang
        )

    override suspend fun getTransformCitiesbyGeocodingCoordinates(
        lat: Double,
        lon: Double,
        localLang: String
    ): Map<String, LatLng> =
        openWeatherSourse.transformGeocodingToLocalLangGeocoding(
            listCities = getCitiesbyGeocodingCoordinates(lat = lat, lon = lon),
            localLang = localLang
        )
}