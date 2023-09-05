package com.rodrigoguerrero.myfinances.ui.categories


data class AddCategoryUiState(
    val name: String = "",
    val iconItemPosition: Int? = null,
    val selectedGroup: CategoryGroupUi? = null,
    val groups: List<CategoryGroupUi> = emptyList(),
    val isExpense: Boolean = true,
    val navigateBack: Boolean = false,
    val isNameEmpty: Boolean = false,
    val isGroupSelected: Boolean = true,
) {
    fun hasErrors(): Boolean = isNameEmpty && !isGroupSelected
}
