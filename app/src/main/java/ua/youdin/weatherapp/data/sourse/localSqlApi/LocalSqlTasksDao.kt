package ua.youdin.weatherapp.data.sourse.localSqlApi

import androidx.room.*
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CurentWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB


@Dao
interface LocalSqlTasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun recordSelectedCity(city: CitiesUserSelectedDB): Long

    @Update
    fun updateSelectedCity(city: CitiesUserSelectedDB)

    @Query("SELECT * FROM cities_user_selected")
    fun selectionSelectedCity(): List<CitiesUserSelectedDB>

    @Query("SELECT * FROM cities_user_selected WHERE city_id = :cityId")
    fun selectionSelectedCityByID(cityId: Long): CitiesUserSelectedDB

    @Query("DELETE FROM cities_user_selected WHERE city_id = :cityId")
    fun deleteSelectedCityByID(cityId: Long)

    @Query("DELETE FROM cities_user_selected")
    fun deleteAllCities()

    @Query("SELECT * FROM cities_user_selected")
    fun getAllSelectedCities(): List<CitiesUserSelectedDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurentWeather_forCity(curentWeather: CurentWeatherDB)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCurentWeather_forCity(curentWeather: CurentWeatherDB): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHurlyWeather_forCity(hurlyWeather: List<HourlyWeatherDB>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateHurlyWeather_forCity(hurlyWeather: List<HourlyWeatherDB>): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDailytWeather_forCity(dailytWeather: List<DailyWeatherDB>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDailytWeather_forCity(dailytWeather: List<DailyWeatherDB>): Int

    @Query("SELECT * FROM daily_weather WHERE city_id = :cityId")
    fun getListDailyWeatherDB_ByCityID(cityId: Int): List<DailyWeatherDB>

    @Query("SELECT * FROM hourly_weather WHERE city_id = :cityId")
    fun getListHourlyWeatherDB_ByCityID(cityId: Int): List<HourlyWeatherDB>

}
