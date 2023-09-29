package com.rodrigoguerrero.myfinances.ui.transactioins.models

data class TransactionListUiState(
    val transactionsByDate: Map<GroupDateItem, List<TransactionItem>> = emptyMap(),
)
