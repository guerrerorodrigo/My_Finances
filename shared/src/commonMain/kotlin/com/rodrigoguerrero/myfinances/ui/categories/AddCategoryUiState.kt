package com.rodrigoguerrero.myfinances.ui.categories


data class AddCategoryUiState(
    val name: String = "",
    val iconItemPosition: Int? = null,
    val selectedGroup: String = "",
    val groups: List<String> = emptyList(),
    val isExpense: Boolean = true,
)
