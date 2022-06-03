package com.waigoma.google

import com.google.api.client.util.DateTime

class DateTimeManager {
    companion object {
        fun create(year: String, month: String, day: String, hour: String, minute: String): DateTime {
            // yyyy-MM-ddThh:mm:00+09:00 (JST)
            return DateTime("$year-$month-${day}T$hour:$minute:00+09:00")
        }
    }
}