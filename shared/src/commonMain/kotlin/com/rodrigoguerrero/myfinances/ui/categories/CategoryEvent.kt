package com.rodrigoguerrero.myfinances.ui.categories

sealed interface CategoryEvent {
    data class OnNameUpdated(val value: String) : CategoryEvent
    data class OnGroupSelected(val group: CategoryGroupUi) : CategoryEvent
    data object OnToggleTransactionType : CategoryEvent
    data object Validate : CategoryEvent
    data object Save : CategoryEvent
}
