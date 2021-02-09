package ua.youdin.weatherapp.ui.weather

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import java.util.*


@SuppressLint("SetTextI18n", "ResourceAsColor")
@BindingAdapter(
    value = ["app:dateTransform", "app:timeZone", "app:interfaceLanguage"],
    requireAll = true
)
fun dateTransform(textView: TextView, date: Long, zone: String, interfaceLanguage: String) {
    val languageTag = correctLocaleData(interfaceLanguage)//.replace("_", "-")
    val dt = DateTime(date * 1000L, DateTimeZone.forID(zone))
    val fmt: DateTimeFormatter = DateTimeFormat.forPattern("dd MMMM yyyy")
    val color: Int = color(dt.dayOfWeek)
    val dayOfWeek = dayOfWeek(dt, languageTag)
    val localDateFmt = dt.toString(dateTimeFormatter(fmt, languageTag))
    textView.apply {
        this.text = "$dayOfWeek\t\t$localDateFmt"
        this.setTextColor(
            ContextCompat.getColor(
                this.context,
                color
            )
        )
    }
}


@SuppressLint("SetTextI18n")
@BindingAdapter("app:temperature")
fun temperature(textView: TextView, day: DailyWeatherDB) {
    textView.text = "${day.temp_day.toInt()} / ${day.temp_night.toInt()}"
}

@ExperimentalUnsignedTypes
@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["app:weatherDescription", "app:temperatureUnits"], requireAll = true)
fun weatherDescription(view: TextView, weather: DailyWeatherDB, temperatureUnits: Boolean) {
    view.text =
        weather.weather_description.toUpperCase(Locale.ROOT) + "\n" +
                "${view.resources.getString(R.string.clouds_description)}: ${weather.clouds.toUInt()} %." + "\n" +
                "${view.resources.getString(R.string.rain_description)}: ${weather.pop.toUInt()} %." + "\n"
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["app:summarizeTemperature", "app:temperatureUnits"], requireAll = true)
fun summarizeTemperature(view: TextView, weather: DailyWeatherDB, temperatureUnits: Boolean) {
    view.text = (
            view.resources.getString(R.string.feels_like_description) +
                    ", ${converterDegrFahr(temperatureUnits)}:  ${weather.feels_like_day.toInt()}/${weather.feels_like_night.toInt()}" + "\n" +
                    view.resources.getString(R.string.dayTemperature_min_max) +
                    ", ${converterDegrFahr(temperatureUnits)}:  ${weather.temp_min.toInt()}/${weather.temp_max.toInt()}" + "\n" +
                    view.resources.getString(R.string.nightTemperature_min_max) +
                    " ${converterDegrFahr(temperatureUnits)}:  ${weather.temp_eve.toInt()}/${weather.temp_morn.toInt()}" + "\n" +
                    "${view.resources.getString(R.string.wind_speed_description)}, ${
                        convertermetricImperial(
                            temperatureUnits,
                            view
                        )
                    }:" +
                    " ${weather.wind_speed.toInt()}")
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["app:summarizeWeather", "app:temperatureUnits"], requireAll = true)
fun summarizeWeather(view: TextView, weather: DailyWeatherDB, temperatureUnits: Boolean) {
    view.text = ("${view.resources.getString(R.string.precipitation_volume)}, mm: " +
            (if (weather.rain != null) {
                "${weather.rain}"
            } else "0.0") + "\n" +
            "${view.resources.getString(R.string.snow_volume)}, mm: " + (if (weather.snow != null) {
        "${weather.snow}"
    } else "0.0") + "\n" +
            "${view.resources.getString(R.string.humidity_description)}, hPa: ${weather.humidity.toInt()}" + "\n" +
            "${view.resources.getString(R.string.pressure_description)}, mm: ${weather.pressure.toInt()}")
}

@SuppressLint("SetTextI18n")
@BindingAdapter(
    value = ["app:summarizeTemperatureWeather", "app:temperatureUnits"],
    requireAll = true
)
fun summarizeTemperatureWeather(
    view: TextView,
    weather: DailyWeatherDB,
    temperatureUnits: Boolean
) {
    view.text = (
            view.resources.getString(R.string.feels_like_description) +
                    ", ${converterDegrFahr(temperatureUnits)}:  ${weather.feels_like_day.toInt()}/${weather.feels_like_night.toInt()}" + "\n" +
                    view.resources.getString(R.string.dayTemperature_min_max) +
                    ", ${converterDegrFahr(temperatureUnits)}:  ${weather.temp_min.toInt()}/${weather.temp_max.toInt()}" + "\n" +
                    view.resources.getString(R.string.nightTemperature_min_max) +
                    " ${converterDegrFahr(temperatureUnits)}:  ${weather.temp_eve.toInt()}/${weather.temp_morn.toInt()}" + "\n" +
                    "${view.resources.getString(R.string.precipitation_volume)}, mm: " +
                    (if (weather.rain != null) {
                        "${weather.rain}"
                    } else "0.0") + "\n" +
                    "${view.resources.getString(R.string.snow_volume)}, mm: " + (if (weather.snow != null) {
                "${weather.snow}"
            } else "0.0") + "\n" +
                    "${view.resources.getString(R.string.humidity_description)}, hPa: ${weather.humidity.toInt()}" + "\n" +
                    "${view.resources.getString(R.string.pressure_description)}, mm: ${weather.pressure.toInt()}" + "\n" +
                    "${view.resources.getString(R.string.wind_speed_description)}, ${
                        convertermetricImperial(
                            temperatureUnits,
                            view
                        )
                    }:" +
                    " ${weather.wind_speed.toInt()}")
}
