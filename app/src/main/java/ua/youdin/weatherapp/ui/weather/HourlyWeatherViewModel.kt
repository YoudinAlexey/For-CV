package ua.youdin.weatherapp.ui.weather

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.youdin.weatherapp.data.TasksForRepository
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.HourlyWeatherDB

class HourlyWeatherViewModel(private val repository: TasksForRepository) : ViewModel() {
    private var _listHourlyWeatherRecycler = MutableLiveData<List<HourlyWeatherDB>>(emptyList())
    var listHourlyWeatherRecycler: LiveData<List<HourlyWeatherDB>> = _listHourlyWeatherRecycler
    var emptyHourlyList: LiveData<Boolean> =
        Transformations.map(listHourlyWeatherRecycler) { list ->
            list.isNullOrEmpty()
        }

    fun getListHourlyWeatherDBByCityID(currentOpeningCity: Int) {
        viewModelScope.launch {
            _listHourlyWeatherRecycler.value =
                repository.getListHourlyWeatherDB_ByCityID(currentOpeningCity)
        }
    }
}