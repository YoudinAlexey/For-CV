package ua.youdin.weatherapp.ui.settings

import ua.youdin.weatherapp.data.sourse.localSqlApi.tables.CitiesUserSelectedDB

data class CitiesUserSelectedForDelete(
    val city: CitiesUserSelectedDB = CitiesUserSelectedDB(),
    var isForDelete: Boolean = false
)