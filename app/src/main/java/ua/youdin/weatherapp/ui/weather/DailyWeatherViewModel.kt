package ua.youdin.weatherapp.ui.weather

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.youdin.weatherapp.data.TasksForRepository
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.DailyWeatherDB

class DailyWeatherViewModel(private val repository: TasksForRepository) : ViewModel() {
    private var _listDailyWeatherRecycler = MutableLiveData<List<DailyWeatherDB>>(emptyList())
    var listDailyWeatherRecycler: LiveData<List<DailyWeatherDB>> = _listDailyWeatherRecycler
    var emptyDailyList: LiveData<Boolean> = Transformations.map(listDailyWeatherRecycler) { list ->
        list.isNullOrEmpty()
    }
    fun getListDailyWeatherDBByCityID(currentOpeningCity: Int) {
        viewModelScope.launch {
            _listDailyWeatherRecycler.value = repository.getListDailyWeatherDB_ByCityID(currentOpeningCity)
        }
    }
}