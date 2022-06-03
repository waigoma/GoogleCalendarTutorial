package com.waigoma

import com.google.api.services.calendar.Calendar
import com.waigoma.google.Connector
import com.waigoma.google.DateTimeManager
import com.waigoma.google.calendar.GetEvent
import com.waigoma.google.calendar.SetEvent

class Main {
    companion object {
        lateinit var service: Calendar

        const val APP_NAME = "GoogleCalendarTutorial"
        const val calendarAddress = ""
    }

    private val keyFilePath = "tutorial/key.json"

    fun start() {
        service = Connector.create(keyFilePath)

        val startDateTime = DateTimeManager.create("2022", "05", "26", "09", "00")
        val endDateTime = DateTimeManager.create("2022", "05", "26", "10", "00")

        val summary = "Tutorial"
        val description = "This is a tutorial event"

        val event = SetEvent.createEvent(summary, description, startDateTime, endDateTime)
        SetEvent.registerEvent(event)

        val min = DateTimeManager.create("2022", "05", "25", "09", "00")
        val max = DateTimeManager.create("2022", "05", "27", "09", "00")

        val events = GetEvent.getDuringEvent(min, max)

        println(events)
    }
}

fun main() {
    Main().start()
}