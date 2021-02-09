package ua.youdin.weatherapp.data

import com.google.android.gms.maps.model.LatLng
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB
import ua.youdin.weatherapp.data.sourse.openWeatherApi.geocodingdata.GeocodingItem


interface TasksForRepository {

    suspend fun recordSelectedCity(
        city: Pair<String, LatLng>,
        temperature_units: Boolean,
        interface_language: String
    )

    suspend fun updateSelectedCity(
        city: CitiesUserSelectedDB,
        temperature_units: Boolean,
        interface_language: String
    )

    suspend fun selectionSelectedCity(): List<CitiesUserSelectedDB>

    suspend fun deleteSelectedCity_ByID(cityId: Long)

    suspend fun refreshAll(
        temperature_units: Boolean,
        interface_language: String
    )

    suspend fun refreshOne(city: String)

    suspend fun getListDailyWeatherDB_ByCityID(cityId: Int): List<DailyWeatherDB>

    suspend fun getListHourlyWeatherDB_ByCityID(citiId: Int): List<HourlyWeatherDB>

    suspend fun getCoordinatesbyGeocodingCities(city: String): List<GeocodingItem>

    suspend fun getCitiesbyGeocodingCoordinates(lat: Double, lon: Double): List<GeocodingItem>

    suspend fun getTransformCoordinatesbyGeocodingCities(
        city: String,
        localLang: String
    ): Map<String, LatLng>

    suspend fun getTransformCitiesbyGeocodingCoordinates(
        lat: Double,
        lon: Double,
        localLang: String
    ): Map<String, LatLng>


}

