package com.rodrigoguerrero.myfinances.ui.categories.models

sealed interface CreateCategoryEvent {
    data class OnNameUpdated(val value: String) : CreateCategoryEvent
    data class OnGroupSelected(val group: CategoryGroupUi) : CreateCategoryEvent
    data object OnToggleTransactionType : CreateCategoryEvent
    data object Validate : CreateCategoryEvent
    data object Save : CreateCategoryEvent
    data class UpdateIconPosition(val position: Int?) : CreateCategoryEvent
}
