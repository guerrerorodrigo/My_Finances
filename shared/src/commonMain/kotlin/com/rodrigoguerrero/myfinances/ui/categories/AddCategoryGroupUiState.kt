package com.rodrigoguerrero.myfinances.ui.categories

data class AddCategoryGroupUiState(
    val group: String = "",
    val isComplete: Boolean = true,
    val navigateBack: Boolean = false,
)
