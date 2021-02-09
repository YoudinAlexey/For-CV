package ua.youdin.weatherapp.data.sourse.openWeatherApi

import android.os.Build
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ua.youdin.weatherapp.data.sourse.openWeatherApi.Moshi.moshi
import ua.youdin.weatherapp.data.sourse.openWeatherApi.geocodingdata.GeocodingItem
import ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata.WeatherDataNet


private val BASE_URL =
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) "https://api.openweathermap.org/" else "http://api.openweathermap.org/"

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}
val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

object Moshi {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
//    .client(client)
    .build()

interface OpenWeatherServise {
    @GET("data/2.5/onecall?exclude=minutely")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String,
        @Query("units") units: String
    ): Deferred<WeatherDataNet>

    @GET("geo/1.0/direct?limit=5")
    fun directGeocoding (
        @Query("q") q: String
    ): Deferred<List<GeocodingItem>>

    @GET("geo/1.0/reverse?limit=5")
    fun reverseGeocoding (
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Deferred<List<GeocodingItem>>
}

object OpenWeather {
    val retrofitService: OpenWeatherServise by lazy { retrofit.create(OpenWeatherServise::class.java) }
}