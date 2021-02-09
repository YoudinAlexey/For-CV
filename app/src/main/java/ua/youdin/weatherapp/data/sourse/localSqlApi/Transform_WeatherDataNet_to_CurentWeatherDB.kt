package ua.youdin.weatherapp.data.sourse.localSqlApi

import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CurentWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB
import ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata.WeatherDataNet


fun WeatherDataNet.asCurentWeather(cityId: Long): CurentWeatherDB {
    return CurentWeatherDB(
        latitude = this.lat,
        longitude = this.lon,
        timezone = this.timezone,
        timezone_offset = this.timezone_offset,

        dt = this.current.dt,
        temp = this.current.temp,
        feels_like = this.current.feels_like,
        pressure = this.current.pressure,
        humidity = this.current.humidity,
        clouds = this.current.clouds,
        visibility = this.current.visibility,

        weather_id = this.current.weather[0].id,
        weather_main = this.current.weather[0].main,
        weather_description = this.current.weather[0].description,
        weather_icon = this.current.weather[0].icon,

        city_id = cityId
    )
}


fun WeatherDataNet.asHourlyWeather(cityId: Long): List<HourlyWeatherDB> {
    val hourlyWeatherDB: MutableList<HourlyWeatherDB> = mutableListOf()
    this.hourly.forEachIndexed { index, hourly ->
        hourlyWeatherDB.add(
            HourlyWeatherDB(
                latitude = this.lat,
                longitude = this.lon,
                timezone = this.timezone,
                timezone_offset = this.timezone_offset,

                dt = hourly.dt,
                temp = hourly.temp,
                feels_like = hourly.feels_like,
                pressure = hourly.pressure,
                humidity = hourly.humidity,
                clouds = hourly.clouds,
                visibility = hourly.visibility,
                wind_speed = hourly.wind_speed,
                rain = hourly.rain?.`1h`,
                snow = hourly.snow?.`1h`,
                weather_id = hourly.weather[0].id,
                weather_main = hourly.weather[0].main,
                weather_description = hourly.weather[0].description,
                weather_icon = hourly.weather[0].icon,

                hour_number = index,
                city_id = cityId
            )
        )
    }
    return hourlyWeatherDB
}

fun WeatherDataNet.asDailyWeather(cityId: Long): List<DailyWeatherDB> {
    val dailyWeatherDB: MutableList<DailyWeatherDB> = mutableListOf()
    this.daily.forEachIndexed { index, daily ->
        dailyWeatherDB.add(
            DailyWeatherDB(
                latitude = this.lat,
                longitude = this.lon,
                timezone = this.timezone,
                timezone_offset = this.timezone_offset,

                dt = daily.dt,

                temp_day = daily.temp.day,
                temp_min = daily.temp.min,
                temp_max = daily.temp.max,
                temp_night = daily.temp.night,
                temp_eve = daily.temp.eve,
                temp_morn = daily.temp.morn,

                feels_like_day = daily.feels_like.day,
                feels_like_eve = daily.feels_like.eve,
                feels_like_morn = daily.feels_like.morn,
                feels_like_night = daily.feels_like.night,

                pressure = daily.pressure,
                humidity = daily.humidity,
                wind_speed = daily.wind_speed,
                clouds = daily.clouds,
                pop = daily.pop,
                rain = daily.rain,
                snow = daily.snow,

                weather_id = daily.weather[0].id,
                weather_main = daily.weather[0].main,
                weather_description = daily.weather[0].description,
                weather_icon = daily.weather[0].icon,

                day_number = index,
                city_id = cityId
            )
        )
    }
    return dailyWeatherDB
}
