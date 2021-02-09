package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata

data class Temp(
    val day: Double, // Day temperature.
    val eve: Double, // Evening temperature.
    val max: Double, // Max daily temperature.
    val min: Double, //Min daily temperature.
    val morn: Double, //Morning temperature.
    val night: Double //Night temperature.
)