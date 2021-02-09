package ua.youdin.weatherapp.ui.weather

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.format.DateTimeFormatter
import ua.youdin.weatherapp.R
import java.util.*


@BindingAdapter("app:temperatureUnitsImage")
fun temperatureUnitsImage(imgView: ImageView, temperatureUnits: Boolean) {
    if (temperatureUnits) imgView.setImageDrawable(
        ContextCompat.getDrawable(
            imgView.context,
            R.drawable.ic_celsius
        )
    ) else imgView.setImageDrawable(
        ContextCompat.getDrawable(
            imgView.context,
            R.drawable.ic_fahrenheit
        )
    )
}

fun color(color: Int): Int = when (color) {
    DateTimeConstants.MONDAY -> R.color.col_monday
    DateTimeConstants.TUESDAY -> R.color.col_tuesday
    DateTimeConstants.WEDNESDAY -> R.color.col_wednesday
    DateTimeConstants.THURSDAY -> R.color.col_thursday
    DateTimeConstants.FRIDAY -> R.color.col_friday
    DateTimeConstants.SATURDAY -> R.color.col_saturday
    else -> R.color.col_sunday
}

fun converterDegrFahr(temperatureUnits: Boolean): String = if (temperatureUnits) "C" else "F"

fun convertermetricImperial(temperatureUnits: Boolean, view: TextView): String =
    if (temperatureUnits)
        view.resources.getString(R.string.metre_sec)
    else view.resources.getString(R.string.miles_hour)

fun dateTimeFormatter(
    fmt: DateTimeFormatter,
    languageTag: String
): DateTimeFormatter {
    val localDateFmt: DateTimeFormatter =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fmt.withLocale(Locale.forLanguageTag(languageTag))
        } else fmt.withLocale(Locale.ENGLISH)
    return localDateFmt
}

fun dayOfWeek(dt: DateTime, languageTag: String): String? {
    val dayOfWeek = dt.dayOfWeek().let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            it.getAsText(Locale.forLanguageTag(languageTag))
        } else it.asText
    }
    return dayOfWeek
}

// fix differences in encoding betwin locales openweather.com and Locale.forLanguageTag (languageTag)
fun correctLocaleData(str: String): String =
    when (str) {
        "cz" -> "cs"
        "kr" -> "ko"
        "la" -> "lv"
        "he" -> "iw"
        "id" -> "in"
        else -> str.replace("_", "-")
    }
