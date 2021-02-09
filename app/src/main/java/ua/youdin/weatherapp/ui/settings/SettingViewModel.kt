package ua.youdin.weatherapp.ui.settings


import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.youdin.weatherapp.data.TasksForRepository
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB
import ua.youdin.weatherapp.servise.Event


class SettingViewModel(private val repository: TasksForRepository) :
    ViewModel() {
    private var _userCities = MutableLiveData<List<CitiesUserSelectedDB>>()
    val citiesUserSelectedForDelete: LiveData<Event<List<CitiesUserSelectedForDelete>?>> =
        Transformations.map(_userCities) { list ->
            Event(
                list?.map {
                    CitiesUserSelectedForDelete(it)
                }
            )
        }

    fun getCities() =
        viewModelScope.launch {
            _userCities.value = repository.selectionSelectedCity()
        }

    fun deleteSelectedCity(citiesUserSelectedForDelete: CitiesUserSelectedForDelete) {
        viewModelScope.launch {
            repository.deleteSelectedCity_ByID(citiesUserSelectedForDelete.city.city_id.toLong())
            getCities()
        }
    }
}
