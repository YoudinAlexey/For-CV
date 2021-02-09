package ua.youdin.weatherapp.data.sourse.openWeatherApi.geocodingdata


data class GeocodingItem(
    val name: String,
    val local_names: LocalNames,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?,
    )

data class LocalNames(
    val feature_name: String,
    val ascii: String,
    val af: String?,
    val al: String?,
    val ar: String?,
    val az: String?,
    val bg: String?,
    val ca: String?,
    val cz: String?,
    val da: String?,
    val de: String?,
    val el: String?,
    val en: String?,
    val eu: String?,
    val fa: String?,
    val fi: String?,
    val fr: String?,
    val gl: String?,
    val he: String?,
    val hi: String?,
    val hr: String?,
    val hu: String?,
    val id: String?,
    val it: String?,
    val ja: String?,
    val kr: String?,
    val la: String?,
    val lt: String?,
    val mk: String?,
    val no: String?,
    val nl: String?,
    val pl: String?,
    val pt: String?,
    val pt_br: String?,
    val ro: String?,
    val ru: String?,
    val sv: String?,
    val se: String?,
    val sk: String?,
    val sl: String?,
    val sp: String?,
    val es: String?,
    val sr: String?,
    val th: String?,
    val tr: String?,
    val ua: String?,
    val uk: String?,
    val vi: String?,
    val zh_cn: String?,
    val zh_tw: String?,
    val zu: String?,
)