package ua.youdin.weatherapp.data.sourse.localSqlApi.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    primaryKeys = arrayOf("city_id"),
    tableName = "curent_weather",
    indices = arrayOf(Index("latitude", "longitude")),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CitiesUserSelectedDB::class,
            parentColumns = arrayOf("city_id"),
            childColumns = arrayOf("city_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class CurentWeatherDB constructor(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_offset: Int,

    val dt: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Double,
    val humidity: Double,
    val clouds: Double,
    val visibility: Double,

    val weather_id: Int,
    val weather_main: String,
    val weather_description: String,
    val weather_icon: String,

    val city_id:Long,
)

@Entity(
    primaryKeys = arrayOf("city_id", "hour_number"),
    tableName = "hourly_weather",
    indices = arrayOf(Index("city_id", "hour_number", "latitude", "longitude")),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CitiesUserSelectedDB::class,
            parentColumns = arrayOf("city_id"),
            childColumns = arrayOf("city_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class HourlyWeatherDB constructor(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_offset: Int,

    val dt: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Double,
    val humidity: Double,
    val clouds: Double,
    val visibility: Double,
    val wind_speed: Double,
    val rain: Double?,
    val snow: Double?,
    val hour_number: Int,

    val weather_id: Int,
    val weather_main: String,
    val weather_description: String,
    val weather_icon: String,

    val city_id: Long,
)

@Entity(
    primaryKeys = arrayOf("city_id", "day_number"),
    tableName = "daily_weather",
    indices = arrayOf(Index("city_id", "day_number", "latitude", "longitude")),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CitiesUserSelectedDB::class,
            parentColumns = arrayOf("city_id"),
            childColumns = arrayOf("city_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class DailyWeatherDB constructor(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_offset: Int,

    val dt: Long,

    val temp_day: Double,
    val temp_min: Double,
    val temp_max: Double,
    val temp_night: Double,
    val temp_eve: Double,
    val temp_morn: Double,

    val feels_like_day: Double,
    val feels_like_eve: Double,//-
    val feels_like_morn: Double,//-
    val feels_like_night: Double,

    val pressure: Double,
    val humidity: Double,
    val wind_speed: Double,
    val clouds: Double,
    val pop: Double, //% Probability of precipitation
    val rain: Double?, //mm
    val snow: Double?,  //mm
    val day_number: Int,

    val weather_id: Int,
    val weather_main: String,
    val weather_description: String,
    val weather_icon: String,

    val city_id: Long,
)