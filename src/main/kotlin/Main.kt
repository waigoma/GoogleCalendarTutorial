import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import com.google.auth.appengine.AppEngineCredentials
import com.google.auth.http.HttpCredentialsAdapter
import java.io.FileInputStream
import java.util.Collections

class Main {
    private val filePath = ""
    private val calendarAddress = ""

    fun start() {
        println("Hello, World!")
        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        val credentials = AppEngineCredentials.fromStream(FileInputStream(filePath))
            .createScoped(Collections.singleton(CalendarScopes.CALENDAR_EVENTS))

        val service = Calendar.Builder(HTTP_TRANSPORT, JacksonFactory.getDefaultInstance(), HttpCredentialsAdapter(credentials))
            .setApplicationName("GoogleCalendarTutorial")
            .build()

        val startEventDateTime = EventDateTime().setDateTime(DateTime("2022-05-26T09:00:00+09:00"))
        val endEventDateTime = EventDateTime().setDateTime(DateTime("2022-05-26T10:00:00+09:00"))

        val summary = "Tutorial"
        val description = "This is a tutorial event"

        var event = Event()
            .setSummary(summary)
            .setDescription(description)
            .setStart(startEventDateTime)
            .setEnd(endEventDateTime)

        event = service.events().insert(calendarAddress, event).execute()

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