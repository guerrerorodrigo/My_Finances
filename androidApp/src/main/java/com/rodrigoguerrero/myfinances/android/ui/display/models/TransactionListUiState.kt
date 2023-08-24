package com.rodrigoguerrero.myfinances.android.ui.display.models

import androidx.compose.runtime.Stable

@Stable
data class TransactionListUiState(
    val transactionsByDate: Map<GroupDateItem, List<TransactionItem>> = emptyMap(),
)
