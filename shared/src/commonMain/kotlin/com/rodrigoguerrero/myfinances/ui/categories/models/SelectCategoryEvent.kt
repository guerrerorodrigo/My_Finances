package com.rodrigoguerrero.myfinances.ui.categories.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType

sealed interface SelectCategoryEvent {
    data class OnCategorySelected(val category: Category) : SelectCategoryEvent
    data class UpdateTransactionType(val type: TransactionType) : SelectCategoryEvent
}
