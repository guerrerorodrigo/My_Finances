package com.rodrigoguerrero.myfinances.ui.transactioins.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType

data class TransactionItem(
    val type: TransactionType,
    val name: String,
    val category: String,
    val amount: String,
    val iconPosition: Int?,
)
