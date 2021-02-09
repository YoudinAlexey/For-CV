package ua.youdin.weatherapp.fakedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB

object FakeDataSities {
    private val allCitiesUserSelectedDB = mutableListOf<CitiesUserSelectedDB>(
        CitiesUserSelectedDB(latitude=15.98792, longitude=43.953621, name="Khamir YE", city_id=0),
        CitiesUserSelectedDB(latitude=37.492222, longitude=40.960281,name="Reshidi TR", city_id=1)
    )


    private var _userCities = MutableLiveData<List<CitiesUserSelectedDB>>()
    val userCities: LiveData<List<CitiesUserSelectedDB>> = _userCities


    init {
        _userCities.value = allCitiesUserSelectedDB

    }
    fun addCitiesUserSelectedDB (citiesUserSelectedDB:CitiesUserSelectedDB){
        allCitiesUserSelectedDB.add(citiesUserSelectedDB)
    }
    fun removeCitiesUserSelectedDB (city_id: Int){
        allCitiesUserSelectedDB.removeAt(index = city_id)// некоректное удаление но так как индексы и Id совпадают то для тестов годиться
    }
}