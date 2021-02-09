package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata

data class Minutely (
    val dt:Long, // Time of the forecasted data, unix, UTC
    val precipitation: Double, // Precipitation volume, mm
)
