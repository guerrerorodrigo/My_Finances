package com.rodrigoguerrero.myfinances.android.ui.models.display

import androidx.compose.runtime.Stable

@Stable
data class TransactionListUiState(
    val transactionsByDate: Map<GroupDateItem, List<TransactionItem>> = emptyMap(),
)
