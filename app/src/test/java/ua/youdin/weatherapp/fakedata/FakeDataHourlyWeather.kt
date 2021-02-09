package ua.youdin.weatherapp.fakedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB

object FakeDataHourlyWeather {
    val allData = mutableListOf<HourlyWeatherDB>(
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609066800, temp=33.4, feels_like=25.77, pressure=1025.0, humidity=69.0, clouds=0.0, visibility=10000.0, wind_speed=5.48, rain=null,  snow=null, hour_number=0, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609070400, temp=31.5, feels_like=24.98, pressure=1026.0, humidity=83.0, clouds=0.0, visibility=10000.0, wind_speed=4.03, rain=null,  snow=null, hour_number=1, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609074000, temp=28.53, feels_like=22.53, pressure=1027.0, humidity=92.0, clouds=0.0, visibility=10000.0, wind_speed=3.0, rain=null,  snow=null, hour_number=2, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609077600, temp=25.47, feels_like=20.05, pressure=1028.0, humidity=97.0, clouds=0.0, visibility=10000.0, wind_speed=1.61, rain=null,  snow=null, hour_number=3, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609081200, temp=24.44, feels_like=19.22, pressure=1029.0, humidity=98.0, clouds=0.0, visibility=10000.0, wind_speed=1.12, rain=null,  snow=null, hour_number=4, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609084800, temp=23.92, feels_like=18.43, pressure=1029.0, humidity=98.0, clouds=0.0, visibility=10000.0, wind_speed=1.48, rain=null,  snow=null, hour_number=5, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609088400, temp=23.67, feels_like=17.91, pressure=1029.0, humidity=98.0, clouds=0.0, visibility=10000.0, wind_speed=1.92, rain=null,  snow=null, hour_number=6, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609092000, temp=23.5, feels_like=17.49, pressure=1029.0, humidity=98.0, clouds=0.0, visibility=10000.0, wind_speed=2.33, rain=null,  snow=null, hour_number=7, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609095600, temp=23.34, feels_like=16.93, pressure=1030.0, humidity=98.0, clouds=0.0, visibility=10000.0, wind_speed=3.0, rain=null,  snow=null, hour_number=8, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609099200, temp=23.16, feels_like=16.32, pressure=1030.0, humidity=98.0, clouds=20.0, visibility=10000.0, wind_speed=3.74, rain=null,  snow=null, hour_number=9, weather_id=801, weather_main="Clouds", weather_description="few clouds", weather_icon="02n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609102800, temp=22.8, feels_like=16.07, pressure=1030.0, humidity=98.0, clouds=40.0, visibility=10000.0, wind_speed=3.49, rain=null,  snow=null, hour_number=10, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609106400, temp=22.73, feels_like=15.4, pressure=1030.0, humidity=98.0, clouds=53.0, visibility=10000.0, wind_speed=4.52, rain=null,  snow=null, hour_number=11, weather_id=803, weather_main="Clouds", weather_description="broken clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609110000, temp=22.41, feels_like=14.79, pressure=1030.0, humidity=98.0, clouds=44.0, visibility=10000.0, wind_speed=4.97, rain=null,  snow=null, hour_number=12, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609113600, temp=22.06, feels_like=14.14, pressure=1030.0, humidity=98.0, clouds=37.0, visibility=10000.0, wind_speed=5.46, rain=null,  snow=null, hour_number=13, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609117200, temp=21.92, feels_like=13.64, pressure=1030.0, humidity=98.0, clouds=0.0, visibility=10000.0, wind_speed=6.06, rain=null,  snow=null, hour_number=14, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609120800, temp=21.85, feels_like=12.94, pressure=1029.0, humidity=97.0, clouds=0.0, visibility=10000.0, wind_speed=7.14, rain=null,  snow=null, hour_number=15, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609124400, temp=21.69, feels_like=12.54, pressure=1029.0, humidity=97.0, clouds=0.0, visibility=10000.0, wind_speed=7.54, rain=null,  snow=null, hour_number=16, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609128000, temp=21.54, feels_like=11.77, pressure=1029.0, humidity=97.0, clouds=0.0, visibility=10000.0, wind_speed=8.63, rain=null,  snow=null, hour_number=17, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609131600, temp=21.31, feels_like=11.37, pressure=1029.0, humidity=97.0, clouds=0.0, visibility=10000.0, wind_speed=8.86, rain=null,  snow=null, hour_number=18, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609135200, temp=21.38, feels_like=10.89, pressure=1030.0, humidity=97.0, clouds=0.0, visibility=10000.0, wind_speed=9.86, rain=null,  snow=null, hour_number=19, weather_id=800, weather_main="Clear", weather_description="clear sky", weather_icon="01d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609138800, temp=22.64, feels_like=11.71, pressure=1030.0, humidity=96.0, clouds=77.0, visibility=10000.0, wind_speed=10.83, rain=null,  snow=null, hour_number=20, weather_id=803, weather_main="Clouds", weather_description="broken clouds", weather_icon="04d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609142400, temp=24.44, feels_like=13.17, pressure=1029.0, humidity=95.0, clouds=45.0, visibility=10000.0, wind_speed=11.7, rain=null,  snow=null, hour_number=21, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609146000, temp=25.66, feels_like=14.54, pressure=1028.0, humidity=95.0, clouds=32.0, visibility=10000.0, wind_speed=11.7, rain=null,  snow=null, hour_number=22, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609149600, temp=26.74, feels_like=15.39, pressure=1027.0, humidity=95.0, clouds=30.0, visibility=10000.0, wind_speed=12.33, rain=null,  snow=null, hour_number=23, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609153200, temp=27.19, feels_like=15.73, pressure=1027.0, humidity=94.0, clouds=24.0, visibility=10000.0, wind_speed=12.55, rain=null,  snow=null, hour_number=24, weather_id=801, weather_main="Clouds", weather_description="few clouds", weather_icon="02d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609156800, temp=26.78, feels_like=15.35, pressure=1026.0, humidity=94.0, clouds=29.0, visibility=10000.0, wind_speed=12.41, rain=null,  snow=null, hour_number=25, weather_id=802, weather_main="Clouds", weather_description="scattered clouds", weather_icon="03d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609160400, temp=25.47, feels_like=13.75, pressure=1026.0, humidity=95.0, clouds=61.0, visibility=10000.0, wind_speed=12.71, rain=null,  snow=null, hour_number=26, weather_id=803, weather_main="Clouds", weather_description="broken clouds", weather_icon="04d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609164000, temp=24.21, feels_like=12.24, pressure=1026.0, humidity=95.0, clouds=75.0, visibility=10000.0, wind_speed=12.91, rain=null,  snow=null, hour_number=27, weather_id=803, weather_main="Clouds", weather_description="broken clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609167600, temp=23.65, feels_like=11.62, pressure=1026.0, humidity=95.0, clouds=81.0, visibility=10000.0, wind_speed=12.91, rain=null,  snow=null, hour_number=28, weather_id=803, weather_main="Clouds", weather_description="broken clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609171200, temp=23.4, feels_like=11.34, pressure=1026.0, humidity=96.0, clouds=82.0, visibility=10000.0, wind_speed=12.95, rain=null,  snow=null, hour_number=29, weather_id=803, weather_main="Clouds", weather_description="broken clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609174800, temp=23.72, feels_like=11.59, pressure=1025.0, humidity=96.0, clouds=86.0, visibility=10000.0, wind_speed=13.15, rain=null,  snow=null, hour_number=30, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609178400, temp=24.4, feels_like=12.65, pressure=1025.0, humidity=96.0, clouds=88.0, visibility=10000.0, wind_speed=12.62, rain=null,  snow=null, hour_number=31, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609182000, temp=24.93, feels_like=13.12, pressure=1025.0, humidity=96.0, clouds=100.0, visibility=10000.0, wind_speed=12.8, rain=null,  snow=null, hour_number=32, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609185600, temp=25.34, feels_like=13.46, pressure=1024.0, humidity=96.0, clouds=100.0, visibility=10000.0, wind_speed=13.0, rain=null,  snow=null, hour_number=33, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609189200, temp=25.93, feels_like=14.16, pressure=1024.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.97, rain=null,  snow=null, hour_number=34, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609192800, temp=26.96, feels_like=14.92, pressure=1024.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=13.67, rain=null,  snow=null, hour_number=35, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609196400, temp=27.48, feels_like=15.58, pressure=1024.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=13.53, rain=null,  snow=null, hour_number=36, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609200000, temp=27.84, feels_like=16.48, pressure=1024.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.64, rain=null,  snow=null, hour_number=37, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609203600, temp=28.2, feels_like=17.06, pressure=1023.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.35, rain=null,  snow=null, hour_number=38, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609207200, temp=28.18, feels_like=16.84, pressure=1023.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.68, rain=null,  snow=null, hour_number=39, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609210800, temp=28.74, feels_like=17.42, pressure=1022.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.77, rain=null,  snow=null, hour_number=40, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609214400, temp=29.35, feels_like=18.63, pressure=1022.0, humidity=98.0, clouds=100.0, visibility=10000.0, wind_speed=11.92, rain=null,  snow=null, hour_number=41, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609218000, temp=29.84, feels_like=18.55, pressure=1022.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.97, rain=null,  snow=null, hour_number=42, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04n", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609221600, temp=30.27, feels_like=19.58, pressure=1022.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.01, rain=null,  snow=null, hour_number=43, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609225200, temp=30.81, feels_like=19.72, pressure=1023.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.84, rain=null,  snow=null, hour_number=44, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609228800, temp=31.42, feels_like=20.59, pressure=1023.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.55, rain=null,  snow=null, hour_number=45, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609232400, temp=32.0, feels_like=21.34, pressure=1022.0, humidity=97.0, clouds=100.0, visibility=10000.0, wind_speed=12.37, rain=null,  snow=null, hour_number=46, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
        HourlyWeatherDB(latitude=50.0, longitude=36.25, timezone="Europe/Kiev", timezone_offset=7200, dt=1609236000, temp=32.49, feels_like=21.61, pressure=1022.0, humidity=96.0, clouds=100.0, visibility=10000.0, wind_speed=12.82, rain=null,  snow=null, hour_number=47, weather_id=804, weather_main="Clouds", weather_description="overcast clouds", weather_icon="04d", city_id=1),
)
    // список HourlyWeather
    private var _listHourlyWeatherRecycler = MutableLiveData<List<HourlyWeatherDB>>(null)
    val listHourlyWeatherRecycler: LiveData<List<HourlyWeatherDB>> =  _listHourlyWeatherRecycler

    init {
        _listHourlyWeatherRecycler.value = allData
    }
}