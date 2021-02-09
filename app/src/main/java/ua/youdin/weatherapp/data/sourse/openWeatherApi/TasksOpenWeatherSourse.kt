package ua.youdin.weatherapp.data.sourse.openWeatherApi


import com.google.android.gms.maps.model.LatLng
import ua.youdin.weatherapp.data.sourse.openWeatherApi.geocodingdata.GeocodingItem
import ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata.WeatherDataNet
import ua.youdin.weatherapp.servise.Result


interface TasksOpenWeatherSourse {

    suspend fun loadWeather(
        city: Pair<String, LatLng>,
        temperature_units: String,
        interface_language: String,
    ): Result<WeatherDataNet>

    suspend fun directGeocoding (
        city:String
    ): Result<List<GeocodingItem>>

    suspend fun reverseGeocoding (
        lat: Double,
        lon: Double
    ): Result<List<GeocodingItem>>

    suspend fun transformGeocodingToLocalLangGeocoding(listCities: List<GeocodingItem>, localLang:String): Map<String, LatLng>
}