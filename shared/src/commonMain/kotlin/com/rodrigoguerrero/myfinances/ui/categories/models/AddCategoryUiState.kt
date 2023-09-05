package com.rodrigoguerrero.myfinances.ui.categories.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType


data class AddCategoryUiState(
    val name: String = "",
    val iconItemPosition: Int? = null,
    val selectedGroup: CategoryGroupUi? = null,
    val groups: List<CategoryGroupUi> = emptyList(),
    val transactionType: TransactionType = TransactionType.EXPENSE,
    val navigateBack: Boolean = false,
    val isNameEmpty: Boolean = false,
    val isGroupSelected: Boolean = true,
) {
    fun hasErrors(): Boolean = isNameEmpty && !isGroupSelected
}
