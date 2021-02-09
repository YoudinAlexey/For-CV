package ua.youdin.weatherapp.ui.findCity


import android.app.Application
import androidx.lifecycle.*
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import ua.youdin.weatherapp.R
import ua.youdin.weatherapp.data.TasksForRepository
import ua.youdin.weatherapp.servise.Event
import ua.youdin.weatherapp.servise.State
import ua.youdin.weatherapp.servise.State.*
import ua.youdin.weatherapp.servise.debounce
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_CITY
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_LANGUAGE
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_LATITUDE
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_LONGTIDUDE
import ua.youdin.weatherapp.ui.findCity.FindCityFragment.Companion.KEY_TEMPERATURE_UNITS
import ua.youdin.weatherapp.work.LoadDataWorker
import ua.youdin.weatherapp.work.LoadDataWorker.Companion.LOADDATAWORKER_NAME


class FindCityViewModel(private val repository: TasksForRepository, application: Application) :
    AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
    var textInFieldFindCity = MutableLiveData<String>()

    private var _stateTextErrorFieldFindCity: LiveData<State> =
        Transformations.map(textInFieldFindCity) { field ->
            when (field.isEmpty()) {
                true -> InValid
                false -> Valid
            }
        }

    var textErrorFieldFindCity: LiveData<String?> =
        Transformations.map(_stateTextErrorFieldFindCity) { s ->
            when (s) {
                Valid -> null
                InValid -> getApplication<Application>().applicationContext.resources.getString(R.string.error)
                ErrorLoading -> getApplication<Application>().applicationContext.resources.getString(
                    R.string.errorFindCity
                ) // TODO не используем пока
            }
        }.debounce(200L, viewModelScope)

    private var _loadCoordinates = MutableLiveData<Event<Map<String, LatLng>>>()
    var loadedCoordinates: LiveData<Event<Map<String, LatLng>>> = _loadCoordinates

    private var _loadSities = MutableLiveData<Event<Map<String, LatLng>>>()
    var loadedSities: LiveData<Event<Map<String, LatLng>>> = _loadSities

    fun loadCoordinates(city: String, localLang: String) =
        viewModelScope.launch {
            _loadCoordinates.value = Event(
                repository.getTransformCoordinatesbyGeocodingCities(
                    city = city,
                    localLang = localLang
                )
            )
        }

    fun loadCities(lat: Double, lon: Double, localLang: String) =
        viewModelScope.launch {
            _loadSities.value = Event(
                repository.getTransformCitiesbyGeocodingCoordinates(
                    lon = lon,
                    lat = lat,
                    localLang = localLang
                )
            )
        }

    @KoinApiExtension
    fun recordSelectedCity(
        city: Pair<String, LatLng>,
        temperature_units: Boolean,
        interface_language: String
    ) {
        val data = Data.Builder().apply {
            this.putString(KEY_CITY, city.first)
            this.putDouble(KEY_LATITUDE, city.second.latitude)
            this.putDouble(KEY_LONGTIDUDE, city.second.longitude)
            this.putBoolean(KEY_TEMPERATURE_UNITS, temperature_units)
            this.putString(KEY_LANGUAGE, interface_language)
        }.build()
        val loadDataWorkerBuilder = OneTimeWorkRequestBuilder<LoadDataWorker>().apply {
            this.setInputData(data)
        }.build()
        workManager.enqueueUniqueWork(
            LOADDATAWORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            loadDataWorkerBuilder
        )
    }
}
