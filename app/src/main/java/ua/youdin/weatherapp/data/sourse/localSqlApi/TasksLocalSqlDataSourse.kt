package ua.youdin.weatherapp.data.sourse.localSqlApi



import com.google.android.gms.maps.model.LatLng
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CurentWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB


interface TasksLocalSqlDataSourse {

    suspend fun recordSelectedCity(
        city: Pair<String, LatLng>,
        temperature: String,
        interface_language: String
    ):Long

    suspend fun updateSelectedCity(city: CitiesUserSelectedDB)

    suspend fun selectionSelectedCity(): List<CitiesUserSelectedDB>

    suspend fun deleteSelectedCityByid(
        cityId: Long,
    )

    suspend fun getAllCities(): List<CitiesUserSelectedDB>

    suspend fun saveWeatherForCityById(
        curentWeather: CurentWeatherDB,
        hurlyWeather: List<HourlyWeatherDB>,
        dailytWeather: List<DailyWeatherDB>,
    )

    suspend fun updateWeatherForCityById(
        curentWeather: CurentWeatherDB,
        hurlyWeather: List<HourlyWeatherDB>,
        dailytWeather: List<DailyWeatherDB>
    )

    suspend fun getListDailyWeatherDBByCityID(cityId: Int): List<DailyWeatherDB>

    suspend fun getListHourlyWeatherDBByCityID(cityId: Int): List<HourlyWeatherDB>


}