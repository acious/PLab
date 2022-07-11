package com.acious.plabs.util

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    @JvmStatic
    fun timestampToMillisecond(timestamp: String): Long {
        return Timestamp.valueOf(timestamp).time
    }

    @JvmStatic
    fun timeFormatWithMillisecond(timeMillisecond: Long): String {
        val date = Date(timeMillisecond)
        return SimpleDateFormat("HH.mm.ss", Locale.getDefault()).format(date)
    }
}