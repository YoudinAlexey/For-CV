package ua.youdin.weatherapp.data.sourse.openWeatherApi

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.youdin.weatherapp.data.sourse.openWeatherApi.geocodingdata.GeocodingItem
import ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata.WeatherDataNet
import ua.youdin.weatherapp.servise.Result


class OpenWeatherSourse internal constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : TasksOpenWeatherSourse {

    override suspend fun loadWeather(
        city: Pair<String, LatLng>,
        temperature_units: String,
        interface_language: String
    ): Result<WeatherDataNet> =
        withContext(ioDispatcher) {
            val getWeather = OpenWeather.retrofitService.getWeather(
                lat = city.second.latitude,
                lon = city.second.longitude,
                lang = interface_language,
                units = temperature_units
            )
            try {
                Result.Success(getWeather.await())
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }


    override suspend fun directGeocoding(city: String): Result<List<GeocodingItem>> =
        withContext(ioDispatcher) {
            val getCoordinates = OpenWeather.retrofitService.directGeocoding(q = city)
            try {
                Result.Success(getCoordinates.await())
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }

    override suspend fun reverseGeocoding(
        lat: Double,
        lon: Double
    ): Result<List<GeocodingItem>> =
        withContext(ioDispatcher) {
            val getCities = OpenWeather.retrofitService.reverseGeocoding(lat = lat, lon = lon)
            try {
                Result.Success(getCities.await())
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }


    override suspend fun transformGeocodingToLocalLangGeocoding(
        listCities: List<GeocodingItem>,
        localLang: String
    ): Map<String, LatLng> {
        val map = mutableMapOf<String, LatLng>()
        withContext(ioDispatcher) {
            listCities.map {
                val lll: String = when (localLang) {
                    "af" -> it.local_names.af ?: it.local_names.en ?: it.name
                    "al" -> it.local_names.al ?: it.local_names.en ?: it.name
                    "ar" -> it.local_names.ar ?: it.local_names.en ?: it.name
                    "az" -> it.local_names.az ?: it.local_names.en ?: it.name
                    "bg" -> it.local_names.bg ?: it.local_names.en ?: it.name
                    "ca" -> it.local_names.ca ?: it.local_names.en ?: it.name
                    "cz" -> it.local_names.cz ?: it.local_names.en ?: it.name
                    "da" -> it.local_names.da ?: it.local_names.en ?: it.name
                    "de" -> it.local_names.de ?: it.local_names.en ?: it.name
                    "el" -> it.local_names.el ?: it.local_names.en ?: it.name
                    "en" -> it.local_names.en ?: it.name
                    "eu" -> it.local_names.eu ?: it.local_names.en ?: it.name
                    "fa" -> it.local_names.fa ?: it.local_names.en ?: it.name
                    "fi" -> it.local_names.fi ?: it.local_names.en ?: it.name
                    "fr" -> it.local_names.fr ?: it.local_names.en ?: it.name
                    "gl" -> it.local_names.gl ?: it.local_names.en ?: it.name
                    "he" -> it.local_names.he ?: it.local_names.en ?: it.name
                    "hi" -> it.local_names.hi ?: it.local_names.en ?: it.name
                    "hr" -> it.local_names.hr ?: it.local_names.en ?: it.name
                    "hu" -> it.local_names.hu ?: it.local_names.en ?: it.name
                    "id" -> it.local_names.id ?: it.local_names.en ?: it.name
                    "it" -> it.local_names.it ?: it.local_names.en ?: it.name
                    "ja" -> it.local_names.ja ?: it.local_names.en ?: it.name
                    "kr" -> it.local_names.kr ?: it.local_names.en ?: it.name
                    "la" -> it.local_names.la ?: it.local_names.en ?: it.name
                    "lt" -> it.local_names.lt ?: it.local_names.en ?: it.name
                    "mk" -> it.local_names.mk ?: it.local_names.en ?: it.name
                    "no" -> it.local_names.no ?: it.local_names.en ?: it.name
                    "nl" -> it.local_names.nl ?: it.local_names.en ?: it.name
                    "pl" -> it.local_names.pl ?: it.local_names.en ?: it.name
                    "pt" -> it.local_names.pt ?: it.local_names.en ?: it.name
                    "pt_BR" -> it.local_names.pt_br ?: it.local_names.en ?: it.name
                    "ro" -> it.local_names.ro ?: it.local_names.en ?: it.name
                    "ru" -> it.local_names.ru ?: it.local_names.en ?: it.name
                    "sv" -> it.local_names.sv ?: it.local_names.en ?: it.name
                    "se" -> it.local_names.se ?: it.local_names.en ?: it.name
                    "sk" -> it.local_names.sk ?: it.local_names.en ?: it.name
                    "sl" -> it.local_names.sl ?: it.local_names.en ?: it.name
                    "sp" -> it.local_names.sp ?: it.local_names.en ?: it.name
                    "es" -> it.local_names.es ?: it.local_names.en ?: it.name
                    "sr" -> it.local_names.sr ?: it.local_names.en ?: it.name
                    "th" -> it.local_names.th ?: it.local_names.en ?: it.name
                    "tr" -> it.local_names.tr ?: it.local_names.en ?: it.name
                    "ua" -> it.local_names.ua ?: it.local_names.ru ?: it.name
                    "uk" -> it.local_names.uk ?: it.local_names.ru ?: it.name
                    "vi" -> it.local_names.vi ?: it.local_names.en ?: it.name
                    "zh_CN" -> it.local_names.zh_cn ?: it.local_names.en ?: it.name
                    "zh_TW" -> it.local_names.zh_tw ?: it.local_names.en ?: it.name
                    "zu" -> it.local_names.zu ?: it.local_names.en ?: it.name
                    else -> it.local_names.ascii
                }
                map.put("$lll ${it.country} ${it.state ?: ""}", LatLng(it.lat, it.lon))
            }
        }
        return map
    }
}
