package ua.youdin.weatherapp.data.sourse.openWeatherApi.weatherdata

data class Alert(
    val sender_name: String, // Name of the alert source
    val event:String, //Alert event name
    val start:Long, //Date and time of the start of the alert, Unix, UTC
    val end:Long, //Date and time of the end of the alert, Unix, UTC
    val description: String, //Description of the alert

)
