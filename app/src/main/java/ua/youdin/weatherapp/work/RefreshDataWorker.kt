package ua.youdin.weatherapp.work

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import retrofit2.HttpException
import ua.youdin.weatherapp.data.Repository
import ua.youdin.weatherapp.ui.settings.SettingsPreferenceFragment

@KoinApiExtension
class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params), KoinComponent {
    override suspend fun doWork(): Result {
        val repository: Repository = get()
        try {
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
                .also { sharedPreferences ->
                    repository.refreshAll(
                        sharedPreferences.getBoolean(
                            SettingsPreferenceFragment.TEMPERATURE_UNITS,
                            false
                        ),
                        sharedPreferences.getString(SettingsPreferenceFragment.LANGUE, "en") ?: "en"
                    )
                }
        } catch (e: HttpException) {
            Log.d("Tag", "Work manager ${e.message()}")
            return Result.retry()
        }
        return Result.success()
    }

    companion object {
        const val REFRESHDATAWORKER_NAME = "ua.youdin.weatherapp.work.RefreshDataWorker"
    }
}