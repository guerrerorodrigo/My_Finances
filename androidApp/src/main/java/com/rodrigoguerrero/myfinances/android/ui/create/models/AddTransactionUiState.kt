package com.rodrigoguerrero.myfinances.android.ui.create.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Stable
import com.rodrigoguerrero.myfinances.android.ui.categories.models.Category
import com.rodrigoguerrero.myfinances.android.ui.display.models.TransactionType
import com.rodrigoguerrero.myfinances.utils.formatDate
import com.rodrigoguerrero.myfinances.utils.formatTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Stable
data class AddTransactionUiState(
    val name: String = "",
    val amount: String = "",
    val date: String = formatDate(
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    ),
    val time: String = formatTime(
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    ),
    val category: Category = Category(name = "Other", icon = Icons.Filled.Settings),
    val categoryGroup: String = "Other",
    val notes: String = "",
    val type: TransactionType = TransactionType.EXPENSE,
)
