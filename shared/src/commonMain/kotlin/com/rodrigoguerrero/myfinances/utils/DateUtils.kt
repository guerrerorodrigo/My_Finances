package com.rodrigoguerrero.myfinances.utils

import kotlinx.datetime.LocalDateTime

fun formatDate(currentTime: LocalDateTime): String {
    return "${currentTime.dayOfMonth} ${
        currentTime.month.name
            .lowercase()
            .replaceFirstChar { currentTime.month.name[0].uppercase() }
    } ${currentTime.year}"
}
