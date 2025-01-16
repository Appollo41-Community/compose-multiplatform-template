package com.appollo41.app.core.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun Instant.formatToDateTime(): String {
    val localDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())

    val day = if (localDateTime.dayOfMonth < 10) "0${localDateTime.dayOfMonth}" else "${localDateTime.dayOfMonth}"
    val month = if (localDateTime.monthNumber < 10) "0${localDateTime.monthNumber}" else "${localDateTime.monthNumber}"
    val year = localDateTime.year
    val hour = if (localDateTime.hour < 10) "0${localDateTime.hour}" else "${localDateTime.hour}"
    val minute = if (localDateTime.minute < 10) "0${localDateTime.minute}" else "${localDateTime.minute}"

    return "$day.$month.$year. $hour:$minute"
}

fun epochTimeMillisecondsFormat(epochTimeMilliseconds: Long?): String? {
    if (epochTimeMilliseconds == null) {
        return null
    }

    val timeZone = TimeZone.currentSystemDefault()
    val instant = Instant.fromEpochMilliseconds(epochTimeMilliseconds)
    val localDateTime = instant.toLocalDateTime(timeZone)

    return "${localDateTime.dayOfMonth.toString().padStart(2, '0')}.${
        localDateTime.monthNumber.toString().padStart(2, '0')
    }.${localDateTime.year}."
}

fun compareDates(firstDate: Long?, secondDate: Long?): Boolean =
    epochTimeMillisecondsFormat(firstDate).equals(epochTimeMillisecondsFormat(secondDate))

fun mapDayToSerbian(day: String): String {
    val dayMapping = mapOf(
        "MON" to "PON",
        "TUE" to "UTO",
        "WED" to "SRE",
        "THU" to "ČET",
        "FRI" to "PET",
        "SAT" to "SUB",
        "SUN" to "NED"
    )
    return dayMapping[day.uppercase()] ?: day
}