package com.rodrigoguerrero.myfinances.ui.transactioins.models

import androidx.compose.runtime.Stable
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.categories.models.Category
import com.rodrigoguerrero.myfinances.utils.formatDate
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Stable
data class AddTransactionUiState(
    val name: String = "",
    val amount: String = "",
    val category: Category = Category(
        name = "Other",
        iconPosition = null,
        id = 1,
        group = "Other",
        groupId = 1,
    ),
    val notes: String = "",
    val type: TransactionType = TransactionType.EXPENSE,
    val navigateBack: Boolean = false,
    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false,
    private val dateMillis: Long? = Clock.System.now().toEpochMilliseconds(),
    val hour: Int = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).hour,
    val minutes: Int = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).minute,
) {
    val date: String
        get() {
            return formatDate(
                Instant
                    .fromEpochMilliseconds(dateMillis ?: Clock.System.now().toEpochMilliseconds())
                    .toLocalDateTime(TimeZone.currentSystemDefault())
            )
        }

    val time: String
        get() {
            return LocalTime(hour = hour, minute = minutes).toString()
        }
}
