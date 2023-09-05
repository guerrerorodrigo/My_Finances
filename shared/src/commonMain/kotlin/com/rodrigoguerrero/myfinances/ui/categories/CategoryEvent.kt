package com.rodrigoguerrero.myfinances.ui.categories

sealed interface CategoryEvent {
    data class OnNameUpdated(val value: String) : CategoryEvent
    data class OnGroupSelected(val group: String) : CategoryEvent
    data object OnToggleTransactionType : CategoryEvent
}
