package ua.youdin.weatherapp.data.sourse.openWeatherApi

import ua.youdin.weatherapp.R

fun weatherConditionCodes (code: Int): Pair<Int, Int> =
    when(code){
        200 -> R.drawable.ic_11d_1 to R.drawable.ic_11n_1    // Гроза гроза с небольшим дождем 11д.
        201 -> R.drawable.ic_11d_2 to R.drawable.ic_11n_2     // Гроза гроза с дождем 11д
        202 -> R.drawable.ic_11d_3 to R.drawable.ic_11n_3     // Гроза гроза с сильным дождем 11д
        210 -> R.drawable.ic_11d_1 to R.drawable.ic_11n_1     // Гроза легкая гроза 11д
        211 -> R.drawable.ic_11d_2 to R.drawable.ic_11n_2     // Гроза гроза 11д
        212 -> R.drawable.ic_11d_3 to R.drawable.ic_11n_3     // Гроза сильная гроза 11д
        221 -> R.drawable.ic_11d_3 to R.drawable.ic_11n_3     // Гроза оборванная гроза 11д
        230 -> R.drawable.ic_11d_1 to R.drawable.ic_11n_1     // Гроза, гроза, небольшой дождь 11д
        231 -> R.drawable.ic_11d_2 to R.drawable.ic_11n_2     // Гроза гроза с моросью 11д
        232 -> R.drawable.ic_11d_3 to R.drawable.ic_11n_3     // Гроза гроза с сильным дождем 11д

        300 -> R.drawable.ic_09d_1 to R.drawable.ic_09n_1     // Морось слабая интенсивность морось 09d
        301 -> R.drawable.ic_09d_2 to R.drawable.ic_09n_2    // Морось изморось 09d
        302 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3     // Морось сильная морось 09d
        310 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3      // Морось слабая интенсивность моросящий дождь 09d
        311 -> R.drawable.ic_09d_2 to R.drawable.ic_09n_2     // Морось Моросящий дождь 09d
        312 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3      // Морось сильный моросящий дождь 09д
        313 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3      // Морось Моросящий дождь дождь и изморось 09д
        314 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3     // Морось сильный ливень дождь с моросью 09д
        321 -> R.drawable.ic_09d_09n_4 to R.drawable.ic_09d_09n_4    // Морось  Дождь изморозь 09d

        500 -> R.drawable.ic_10d to R.drawable.ic_10n     // Дождь небольшой дождь 10д
        501 -> R.drawable.ic_10d to R.drawable.ic_10n     // Дождь умеренный дождь 10д
        502 -> R.drawable.ic_09d_1 to R.drawable.ic_09n_1     // Дождь сильный дождь 10д
        503 -> R.drawable.ic_09d_2 to R.drawable.ic_09n_2     // Дождь очень сильный дождь 10д
        504 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3     // Дождь экстремальный дождь 10д
        511 -> R.drawable.ic_09d_09n_4 to R.drawable.ic_09d_09n_4     // Дождь, ледяной дождь 13д
        520 -> R.drawable.ic_11d_1 to R.drawable.ic_11n_1     // Дождь интенсивность света Дождь 09д
        521 -> R.drawable.ic_11d_1 to R.drawable.ic_11n_1     // Дождь дождь дождь 09д
        522 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3     // Дождь сильный ливневый дождь 09д.
        531 -> R.drawable.ic_09d_09n_3 to R.drawable.ic_09d_09n_3     // Дождь рваный дождь дождь 09д

        600 -> R.drawable.ic_13d to R.drawable.ic_13n     // Снег легкий снег 13д
        601 -> R.drawable.ic_13d_1 to R.drawable.ic_13n_1     // Снег Снег 13д
        602 -> R.drawable.ic_13d_2 to R.drawable.ic_13d_2     // Снег Сильный снегопад 13д
        611 -> R.drawable.ic_13d_2 to R.drawable.ic_13d_2     // Снег Снежный снег 13д
        612 -> R.drawable.ic_13d_1 to R.drawable.ic_13n_1     // Снег Light дождь с мокрым снегом 13д
        613 -> R.drawable.ic_13d_1 to R.drawable.ic_13n_1     // Снег Снежный дождь с мокрым снегом 13д
        615 -> R.drawable.ic_13d_1 to R.drawable.ic_13n_1     // Снег Небольшой дождь со снегом 13д
        616 -> R.drawable.ic_09d_09n_4 to R.drawable.ic_09d_09n_4     // Снег, дождь и снег 13д
        620 -> R.drawable.ic_09d_09n_4 to R.drawable.ic_09d_09n_4     // Снег легкий дождь снег 13д
        621 -> R.drawable.ic_13d_1 to R.drawable.ic_13n_1     // Снег Снежный дождь снег 13д
        622 -> R.drawable.ic_13d_2 to R.drawable.ic_13d_2      // Снег Сильный снегопад 13д

        701 -> R.drawable.ic_50_1 to R.drawable.ic_50_1      // Атмосфера Mist Mist 50дн.
        711 -> R.drawable.ic_50_1 to R.drawable.ic_50_1     // Атмосфера Дым Дым 50д
        721 -> R.drawable.ic_50_1 to R.drawable.ic_50_1     // Атмосфера Haze Haze 50d
        731 -> R.drawable.ic_50_2 to R.drawable.ic_50_2     // Атмосфера Пыльный песок / пыльные вихри 50d
        741 -> R.drawable.ic_50_1 to R.drawable.ic_50_1     // Атмосфера Туман туман 50д
        751 -> R.drawable.ic_50_2 to R.drawable.ic_50_2     // Атмосфера Песок песочный 50d
        761 -> R.drawable.ic_50_2 to R.drawable.ic_50_2     // Атмосфера Пыль пыль 50d
        762 -> R.drawable.ic_50_1 to R.drawable.ic_50_1     // Атмосфера Пепельный вулканический пепел 50d
        771 -> R.drawable.ic_50_2 to R.drawable.ic_50_2     // Атмосфера Шквал шквалы 50д.
        781 -> R.drawable.ic_50_3 to R.drawable.ic_50_3     // Атмосфера Торнадо торнадо 50d

        800 -> R.drawable.ic_01d to R.drawable.ic_01n     // Ясное чистое небо 01d
        801 -> R.drawable.ic_02d to R.drawable.ic_02n    // Облака немного облаков: 11-25% 02d
        802 -> R.drawable.ic_03d to R.drawable.ic_03n     // Облака рассеянные облака: 25-50% 03d
        803 -> R.drawable.ic_04d to R.drawable.ic_04d    // Облака, разорванные облака: 51-84% 04d
        804 -> R.drawable.ic_04n to R.drawable.ic_04n     // Облака пасмурные облака: 85-100% 04d
        else -> R.drawable.ic_no_icon to R.drawable.ic_no_icon
}