package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata

data class Weather(
    val description: String, //Weather condition within the group (full list of weather conditions). Get the output in your language
    val icon: String, //Weather icon id.
    val id: Int, //Weather condition id
    val main: String //Group of weather parameters (Rain, Snow, Extreme etc.)
)