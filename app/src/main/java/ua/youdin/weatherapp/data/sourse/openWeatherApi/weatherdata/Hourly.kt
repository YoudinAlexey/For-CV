package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata

data class Hourly(
    val clouds: Double, //Cloudiness, %
    val dew_point: Double, // Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    val dt: Long, //Time of the forecasted data, Unix, UTC
    val feels_like: Double, //Temperature. This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    val humidity: Double,  //Humidity, %
    val pop: Double, //Probability of precipitation Вероятность выпадения осадков
    val pressure: Double, // Atmospheric pressure on the sea level, hPa
    val rain: Rain?,//(where available) Rain volume for last hour, mm
    val snow: Show?, //(where available) Snow volume for last hour, mm
    val wind_gust: WindGust?, //(where available) Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour
    val temp: Double, //Temperature. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    val visibility: Double, //Average visibility, metres
    val weather: List<Weather>, //weather
    val wind_deg: Double, // Wind direction, degrees (meteorological)
    val wind_speed: Double, // Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour
    val uvi: Double, // The maximum value of UV index for the day
)