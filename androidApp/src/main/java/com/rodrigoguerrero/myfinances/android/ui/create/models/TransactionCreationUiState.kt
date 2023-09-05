package com.rodrigoguerrero.myfinances.android.ui.create.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.categories.models.Category

data class TransactionCreationUiState(
    val selectedCategory: Category? = null,
    val transactionType: TransactionType = TransactionType.EXPENSE,
)
