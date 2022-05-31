import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import google.Connector
import google.calendar.SetEvent

class Main {
    companion object {
        lateinit var service: Calendar

        const val APP_NAME = "GoogleCalendarTutorial"
        const val calendarAddress = ""
    }

    private val keyFilePath = "tutorial/key.json"

    fun start() {
        service = Connector.create(keyFilePath)

        val startDateTime = SetEvent.createDateTime("2022", "05", "26", "09", "00")
        val endDateTime = SetEvent.createDateTime("2022", "05", "26", "10", "00")

        val summary = "Tutorial"
        val description = "This is a tutorial event"

        val event = SetEvent.createEvent(summary, description, startDateTime, endDateTime)
        SetEvent.registerEvent(event)

        val events = service.events().list(calendarAddress)
            .setTimeMax(DateTime("2022-05-25T00:00:00+09:00"))
            .setTimeMin(DateTime("2022-05-27T00:00:00+09:00"))
            .execute()

        println(events)
    }
}

fun main() {
    Main().start()
}