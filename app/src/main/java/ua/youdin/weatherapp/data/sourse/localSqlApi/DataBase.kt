package ua.youdin.weatherapp.data.sourse.localSqlApi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CurentWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB


@Database(
    entities = [
        CitiesUserSelectedDB::class,
        CurentWeatherDB::class,
        HourlyWeatherDB::class,
        DailyWeatherDB::class
    ], version = 1, exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun taskDao(): LocalSqlTasksDao

    companion object {
        fun getDatabase(context: Context): DataBase =
            synchronized(DataBase::class.java) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "Weather.db"
                ).build()
            }
    }
}
