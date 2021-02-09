package ua.youdin.weatherapp.data.sourse.localSqlApi.tables

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "cities_user_selected",
    indices = arrayOf(
        Index(value = ["city_id"]),
        Index(value = ["latitude"]),
        Index(value = ["longitude"])
    ),
)
@Parcelize
data class CitiesUserSelectedDB @JvmOverloads constructor(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val name: String = "",
    val temperature: String = "metric",
    val interface_language: String = "en",
    @PrimaryKey(autoGenerate = true)
    val city_id: Int = 0,
) : Parcelable
