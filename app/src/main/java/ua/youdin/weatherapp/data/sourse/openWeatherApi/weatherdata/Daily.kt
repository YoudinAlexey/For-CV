package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata

data class Daily(
    val clouds: Double, // Cloudiness, %
    val dew_point: Double, //Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    val dt: Long, //Time of the forecasted data, Unix, UTC
    val feels_like: FeelsLike, //This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit
    val humidity: Double, //Humidity, %
    val pop: Double, // Probability of precipitation
    val pressure: Double, // Atmospheric pressure on the sea level, hPa
    val rain: Double?, //(where available) Precipitation volume, mm
    val snow: Double?, //(where available) Snow volume, mm
    val wind_gust: Double?, //(where available) Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
    val sunrise: Double, //Sunrise time, Unix, UTC
    val sunset: Double, // Sunset time, Unix, UTC
    val temp: Temp, //Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    val uvi: Double, // The maximum value of UV index for the day
    val weather: List<Weather>, //weather
    val wind_deg: Double, //Wind direction, degrees (meteorological)
    val wind_speed: Double // Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
)