package ua.youdin.weatherapp.ui.weather

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB
import ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherConditionCodes
import java.util.*


@SuppressLint("SetTextI18n")
@BindingAdapter(
    value = ["app:timeTransform", "app:timeZone", "app:interfaceLanguage"],
    requireAll = true
)
fun timeTransform(textView: TextView, date: Long, zone: String, interfaceLanguage: String) {
    val languageTag = correctLocaleData(interfaceLanguage)
    val dt = DateTime(date * 1000L, DateTimeZone.forID(zone))
    val dayOfWeek = dayOfWeek(dt, languageTag)
    val fmt: DateTimeFormatter =
        DateTimeFormat.forPattern("HH:mm" + "\n" + "dd MMMM yyyy")
    val color: Int = color(dt.dayOfWeek)

    val localDateFmt = dt.toString(dateTimeFormatter(fmt, languageTag))
    textView.apply {
        this.text = "${dayOfWeek}\n${localDateFmt}"
        this.setTextColor(
            ContextCompat.getColor(
                this.context,
                color
            )
        )
    }
}

@BindingAdapter(
    value = ["app:dateTransform", "app:timeZone", "app:setIcon"],
    requireAll = true
)
fun setIcon(imgView: ImageView, date: Long, zone: String, imgCode: Int) {
    val dt = DateTime(date * 1000L, DateTimeZone.forID(zone))
    val (iconDay, IconHigh) = weatherConditionCodes(imgCode)
    when (dt.hourOfDay) {
        in 6..20 -> {
            imgView.setImageResource(iconDay)
        }
        else -> {
            imgView.setImageResource(IconHigh)
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("app:temperatureHourly")
fun temperatureHourly(textView: TextView, hour: HourlyWeatherDB) {
    textView.text = "${hour.temp.toInt()}"
}

@ExperimentalUnsignedTypes
@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["app:weatherHourlyDescription", "app:temperatureUnits"], requireAll = true)
fun weatherHourlyDescription(view: TextView, weather: HourlyWeatherDB, temperatureUnits: Boolean) {
    view.text =
        "${view.resources.getString(R.string.feels_like)} ${weather.feels_like.toUInt()} " +
                " ${converterDegrFahr(temperatureUnits)}, " + weather.weather_description + ". " +
                "${view.resources.getString(R.string.clouds_description)}  ${weather.clouds.toUInt()} %. " +
                "${view.resources.getString(R.string.visibility)}  ${weather.visibility.toUInt()}" +
                " ${view.resources.getString(R.string.metres).toLowerCase(Locale.ROOT)}."
}

@SuppressLint("SetTextI18n")
@BindingAdapter(
    value = ["summarizeHourlyWeather", "temperatureUnits"],
    requireAll = true
)
fun summarizeHourlyWeather(
    view: TextView,
    weather: HourlyWeatherDB,
    temperatureUnits: Boolean
) {
    view.text = (
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
