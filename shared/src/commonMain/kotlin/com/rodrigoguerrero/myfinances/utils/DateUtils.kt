package com.rodrigoguerrero.myfinances.utils

import kotlinx.datetime.LocalDateTime

fun formatDate(currentTime: LocalDateTime): String {
    return "${currentTime.dayOfMonth} ${
        currentTime.month.name
            .lowercase()
            .replaceFirstChar { currentTime.month.name[0].uppercase() }
    } ${currentTime.year}"
}

fun formatTime(currentTime: LocalDateTime): String {
    val time = currentTime.time.toString()
    return time.substring(startIndex = 0, endIndex = time.lastIndexOf(":"))
}
