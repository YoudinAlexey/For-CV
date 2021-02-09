package ua.youdin.weatherapp.ui.mainactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.youdin.weatherapp.data.TasksForRepository
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.servise.SharedPreferencesDelegated
import kotlin.properties.Delegates

class MainActivityViewModel(
    private val repository: TasksForRepository,
    application: Application
) : AndroidViewModel(application) {

    var temperature_units_preference by Delegates.notNull<Boolean>()
    var interface_language_preference by Delegates.notNull<String>()

    fun refreshPreferences(temperature: Boolean, language: String) {
        temperature_units_preference = temperature
        interface_language_preference = language
    }

    private var _userCities = MutableLiveData<List<CitiesUserSelectedDB>>()
    val userCities: LiveData<List<CitiesUserSelectedDB>> = _userCities
    fun selectionCities() =
        viewModelScope.launch {
            _userCities.value = repository.selectionSelectedCity()
        }


    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading
    fun refreshAll() {
        viewModelScope.launch {
            _dataLoading.value = true
            repository.refreshAll(temperature_units_preference, interface_language_preference)
            _userCities.value = repository.selectionSelectedCity()
            _dataLoading.value = false
        }
    }

    fun refresh() {
        val currentOpeningCity by SharedPreferencesDelegated(
            context = getApplication<Application>().applicationContext,
            key = MainActivity.STATE_CURRENT_CITY,
            defaultValue = MainActivity.DEFAULT_CITY
        )
        viewModelScope.launch {
            _dataLoading.value = true
            repository.refreshOne(currentOpeningCity)
            _userCities.value = repository.selectionSelectedCity()
            _dataLoading.value = false
        }
    }
}