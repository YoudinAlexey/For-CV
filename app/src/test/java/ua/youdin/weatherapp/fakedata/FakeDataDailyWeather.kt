package ua.youdin.weatherapp.fakedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB

object FakeDataDailyWeather {
    val allData = mutableListOf<DailyWeatherDB>(
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609059600, temp_day=29.1, temp_min=22.8, temp_max=33.4, temp_night=22.8, temp_eve=24.44, temp_morn=25.36, feels_like_day=21.67, feels_like_eve=19.22, feels_like_morn=17.62, feels_like_night=16.07, pressure=1026.0, humidity=97.0, wind_speed=5.97, clouds=0.0, pop=0.0, rain=null,  snow=null, day_number=0, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01d", city_id=1),
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609146000, temp_day=25.66, temp_min=21.31, temp_max=27.19, temp_night=25.93, temp_eve=23.65, temp_morn=21.69, feels_like_day=14.54, feels_like_eve=11.62, feels_like_morn=12.54, feels_like_night=14.16, pressure=1028.0, humidity=95.0, wind_speed=11.7, clouds=32.0, pop=0.0, rain=null, snow=null, day_number=1, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03d", city_id=1),
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609232400, temp_day=32.0, temp_min=26.96, temp_max=33.91, temp_night=33.55, temp_eve=33.66, temp_morn=28.74, feels_like_day=21.34, feels_like_eve=22.69, feels_like_morn=17.42, feels_like_night=24.03, pressure=1022.0, humidity=97.0, wind_speed=12.37, clouds=100.0, pop=0.0, rain=null, snow=null, day_number=2, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609318800, temp_day=37.08, temp_min=33.31, temp_max=37.24, temp_night=33.31, temp_eve=36.36, temp_morn=35.06, feels_like_day=28.65, feels_like_eve=27.73, feels_like_morn=26.67, feels_like_night=23.99, pressure=1022.0, humidity=97.0, wind_speed=9.82, clouds=100.0, pop=0.36, rain=0.38, snow=null, day_number=3, weather_id=500, weather_main="Rain", weather_description="light rain", weather_icon="10d", city_id=1),
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609405200, temp_day=32.22, temp_min=31.24, temp_max=32.99, temp_night=32.34, temp_eve=32.65, temp_morn=31.73, feels_like_day=22.98, feels_like_eve=23.74, feels_like_morn=22.17, feels_like_night=23.32, pressure=1020.0, humidity=98.0, wind_speed=9.98, clouds=100.0, pop=0.0, rain=null, snow=null, day_number=4, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609491600, temp_day=35.55, temp_min=31.95, temp_max=35.91, temp_night=35.24, temp_eve=35.55, temp_morn=33.48, feels_like_day=29.55, feels_like_eve=30.76, feels_like_morn=26.13, feels_like_night=29.86, pressure=1020.0, humidity=97.0, wind_speed=5.06, clouds=94.0, pop=0.0, rain=null, snow=null, day_number=5, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609578000, temp_day=33.39, temp_min=31.26, temp_max=34.9, temp_night=31.26, temp_eve=32.04, temp_morn=34.38, feels_like_day=25.61, feels_like_eve=25.74, feels_like_morn=28.2, feels_like_night=24.85, pressure=1023.0, humidity=93.0, wind_speed=7.36, clouds=100.0, pop=0.6, rain=0.88, snow=null, day_number=6, weather_id=500, weather_main="Rain", weather_description="light rain", weather_icon="10d", city_id=1),
        DailyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609664400, temp_day=32.18, temp_min=31.08, temp_max=32.63, temp_night=32.14, temp_eve=32.05, temp_morn=31.19, feels_like_day=26.17, feels_like_eve=26.58, feels_like_morn=25.14, feels_like_night=25.92, pressure=1028.0, humidity=97.0, wind_speed=4.18, clouds=100.0, pop=0.68, rain=null, snow=null, day_number=7, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1)
    )
    // список DailyWeather
    private var _listDailyWeatherRecycler = MutableLiveData<List<DailyWeatherDB>>(null)
    val listDailyWeatherRecycler: LiveData<List<DailyWeatherDB>> = _listDailyWeatherRecycler

    init {
        _listDailyWeatherRecycler.value = allData
    }
}