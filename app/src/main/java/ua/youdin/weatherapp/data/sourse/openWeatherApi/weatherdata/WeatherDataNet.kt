package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata


data class WeatherDataNet(
    val current: Current, //Current weather data API response   Текущий ответ API данных о погоде
    val daily: List<Daily>, //Daily forecast weather data API response
    val hourly: List<Hourly>, //Hourly forecast weather data API response
    val lat: Double, // Geographical coordinates of the location (latitude)
    val lon: Double, // Geographical coordinates of the location (longitude)
    val timezone: String, //Timezone name for the requested location  Название часового пояса для запрошенного местоположения
    val timezone_offset: Int, //Shift in seconds from UTC  Cдвиг в секундах от UTC
    val minutely: List<Minutely>?, // Minute forecast weather data API response
    val alerts: List<Alert>?, //National weather alerts data from major national weather warning systems
)

