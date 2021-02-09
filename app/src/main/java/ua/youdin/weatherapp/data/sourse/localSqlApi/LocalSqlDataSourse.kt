package ua.youdin.weatherapp.data.sourse.localSqlApi

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CurentWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB

class LocalSqlDataSourse internal constructor(
    private val localSqlTasksDao: LocalSqlTasksDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : TasksLocalSqlDataSourse {
    override suspend fun recordSelectedCity(
        city: Pair<String, LatLng>,
        temperature: String,
        interface_language: String
    ): Long =
        withContext(ioDispatcher) {
            CitiesUserSelectedDB(
                name = city.first,
                latitude = city.second.latitude,
                longitude = city.second.longitude,
                temperature = temperature,
                interface_language = interface_language
            ).let {
                localSqlTasksDao.recordSelectedCity(it)
            }
        }

    override suspend fun updateSelectedCity(city: CitiesUserSelectedDB) {
        withContext(ioDispatcher) {
            localSqlTasksDao.updateSelectedCity(city)
        }
    }

    override suspend fun selectionSelectedCity(): List<CitiesUserSelectedDB> =
        withContext(ioDispatcher) {
            localSqlTasksDao.selectionSelectedCity()
        }


    override suspend fun deleteSelectedCityByid(cityId: Long) {
        withContext(ioDispatcher) {
            localSqlTasksDao.deleteSelectedCityByID(cityId = cityId)
        }
    }

    override suspend fun getAllCities(): List<CitiesUserSelectedDB> =
        withContext(ioDispatcher) {
            localSqlTasksDao.getAllSelectedCities()
        }

    override suspend fun saveWeatherForCityById(
        curentWeather: CurentWeatherDB,
        hurlyWeather: List<HourlyWeatherDB>,
        dailytWeather: List<DailyWeatherDB>
    ) {
        withContext(ioDispatcher) {
            localSqlTasksDao.saveCurentWeather_forCity(curentWeather)
            localSqlTasksDao.saveHurlyWeather_forCity(hurlyWeather)
            localSqlTasksDao.saveDailytWeather_forCity(dailytWeather)
        }
    }

    override suspend fun updateWeatherForCityById(
        curentWeather: CurentWeatherDB,
        hurlyWeather: List<HourlyWeatherDB>,
        dailytWeather: List<DailyWeatherDB>
    ) {
        withContext(ioDispatcher) {
            localSqlTasksDao.updateCurentWeather_forCity(curentWeather)
            localSqlTasksDao.updateHurlyWeather_forCity(hurlyWeather)
            localSqlTasksDao.updateDailytWeather_forCity(dailytWeather)
        }
    }

    override suspend fun getListDailyWeatherDBByCityID(cityId: Int): List<DailyWeatherDB> =
        withContext(ioDispatcher) {
            localSqlTasksDao.getListDailyWeatherDB_ByCityID(cityId)
        }

    override suspend fun getListHourlyWeatherDBByCityID(cityId: Int): List<HourlyWeatherDB> =
        withContext(ioDispatcher) {
            localSqlTasksDao.getListHourlyWeatherDB_ByCityID(cityId)
        }
}