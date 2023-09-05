package com.rodrigoguerrero.myfinances.ui.transactioins.models

import androidx.compose.runtime.Stable
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.categories.models.Category
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
    val category: Category = Category(name = "Other", iconPosition = null, id = 1, group = "Other"),
    val notes: String = "",
    val type: TransactionType = TransactionType.EXPENSE,
    val navigateBack: Boolean = false,
)
