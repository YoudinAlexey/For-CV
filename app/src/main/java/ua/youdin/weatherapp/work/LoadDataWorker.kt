package ua.youdin.weatherapp.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.maps.model.LatLng
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import retrofit2.HttpException
import ua.youdin.weatherapp.data.Repository
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_CITY
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_LANGUAGE
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_LATITUDE
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_LONGTIDUDE
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_TEMPERATURE_UNITS


@KoinApiExtension
class LoadDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params), KoinComponent {
    override suspend fun doWork(): Result {
        val repository: Repository = get()
        try {
            inputData.getString(KEY_CITY)?.let {
                val city: Pair<String, LatLng> =
                    (it to LatLng(
                        inputData.getDouble(KEY_LATITUDE, 0.0), inputData.getDouble(
                            KEY_LONGTIDUDE,
                            0.0
                        )
                    ))
                val temperature_units = inputData.getBoolean(KEY_TEMPERATURE_UNITS, true)
                val interface_language = inputData.getString(KEY_LANGUAGE) ?: "en"
                repository.recordSelectedCity(city, temperature_units, interface_language)
            }
        } catch (e: HttpException) {
            Log.d("Tag", "Work manager ${e.message()}")
            return Result.retry()
        }
        return Result.success()
    }

    companion object {
        const val LOADDATAWORKER_NAME = "ua.youdin.weatherapp.work.LoadDataWorker"
    }
}