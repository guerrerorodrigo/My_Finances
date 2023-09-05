package com.rodrigoguerrero.myfinances.ui.categories.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType

data class SelectCategoryUiState(
    val groupsWithCategories: Map<String, List<Category>> = mapOf(),
    val selectedCategory: Category? = null,
    val type: TransactionType = TransactionType.EXPENSE,
)
