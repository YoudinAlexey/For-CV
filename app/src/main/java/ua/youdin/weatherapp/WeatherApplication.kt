package ua.youdin.weatherapp

import android.app.Application
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.youdin.weatherapp.data.Repository
import ua.youdin.weatherapp.data.TasksForRepository
import ua.youdin.weatherapp.data.sourse.localSqlApi.DataBase
import ua.youdin.weatherapp.data.sourse.localSqlApi.DataBase.Companion.getDatabase
import ua.youdin.weatherapp.data.sourse.localSqlApi.LocalSqlDataSourse
import ua.youdin.weatherapp.data.sourse.localSqlApi.TasksLocalSqlDataSourse
import ua.youdin.weatherapp.data.sourse.openWeatherApi.OpenWeatherSourse
import ua.youdin.weatherapp.data.sourse.openWeatherApi.TasksOpenWeatherSourse
import ua.youdin.weatherapp.ui.findCity.FindCityViewModel
import ua.youdin.weatherapp.ui.mainactivity.MainActivityViewModel
import ua.youdin.weatherapp.ui.settings.SettingViewModel
import ua.youdin.weatherapp.ui.weather.DailyWeatherViewModel
import ua.youdin.weatherapp.ui.weather.HourlyWeatherViewModel
import ua.youdin.weatherapp.work.RefreshDataWorker
import java.util.concurrent.TimeUnit


class WeatherApplication : Application(), Configuration.Provider {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    @KoinApiExtension
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(appModule)
        }

        applicationScope.launch {
            setupRecurringWork()
        }
    }

    @KoinApiExtension
    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .build()
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(5, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            RefreshDataWorker.REFRESHDATAWORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return if (BuildConfig.DEBUG) {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .build()
        } else {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.ERROR)
                .build()
        }
    }
}

val appModule = module {
    single(
        named("DataBase"),
        createdAtStart = true
    ) { getDatabase(androidContext()) } bind DataBase::class
    single { LocalSqlDataSourse(get<DataBase>(qualifier = named("DataBase")).taskDao()) } bind TasksLocalSqlDataSourse::class
    single { OpenWeatherSourse() } bind TasksOpenWeatherSourse::class
    single { Repository(get(), get()) } bind TasksForRepository::class
    viewModel { MainActivityViewModel(get<Repository>(), application = androidApplication()) }
    viewModel { FindCityViewModel(get<Repository>(), application = androidApplication()) }
    viewModel { SettingViewModel(get()) }
    viewModel { DailyWeatherViewModel(get()) }
    viewModel { HourlyWeatherViewModel(get()) }
}
