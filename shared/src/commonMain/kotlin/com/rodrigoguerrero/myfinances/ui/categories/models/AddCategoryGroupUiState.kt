package com.rodrigoguerrero.myfinances.ui.categories.models

data class AddCategoryGroupUiState(
    val group: String = "",
    val isComplete: Boolean = true,
    val navigateBack: Boolean = false,
    val isNameEmpty: Boolean = false,
) {
    fun hasErrors() = isNameEmpty
}
