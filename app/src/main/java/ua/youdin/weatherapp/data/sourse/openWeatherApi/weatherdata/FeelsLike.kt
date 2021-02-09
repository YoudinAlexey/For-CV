package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata

data class FeelsLike(
    val day: Double, //Day temperature.
    val eve: Double, // Evening temperature.
    val morn: Double, //Morning temperature.
    val night: Double //Night temperature.
)