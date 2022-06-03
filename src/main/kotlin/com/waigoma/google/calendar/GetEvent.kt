package com.waigoma.google.calendar

import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Events
import com.waigoma.Main

class GetEvent {
    companion object {
        fun getDuringEvent(start: DateTime, end: DateTime): Events {
            return Main.service.events().list(Main.calendarAddress)
                .setTimeMin(start)
                .setTimeMax(end)
                .execute()
        }
    }
}