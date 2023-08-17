package com.rodrigoguerrero.myfinances.android.ui.models.add

import androidx.compose.runtime.Stable
import com.rodrigoguerrero.myfinances.android.ui.models.display.TransactionType

@Stable
data class AddTransactionUiState(
    val name: String = "",
    val amount: String = "",
    val date: String = "",
    val time: String = "",
    val categoryName: String = "",
    val categoryGroup: String = "",
    val notes: String = "",
    val type: TransactionType = TransactionType.EXPENSE,
)
