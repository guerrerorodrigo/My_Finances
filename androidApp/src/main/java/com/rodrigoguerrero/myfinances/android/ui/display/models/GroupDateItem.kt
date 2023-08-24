package com.rodrigoguerrero.myfinances.android.ui.display.models

import androidx.compose.runtime.Immutable

@Immutable
data class GroupDateItem(
    val day: String,
    val date: String,
    val dayTotal: String,
)
