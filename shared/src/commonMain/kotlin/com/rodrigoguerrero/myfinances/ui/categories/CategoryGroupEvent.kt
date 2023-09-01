package com.rodrigoguerrero.myfinances.ui.categories

sealed interface CategoryGroupEvent {
    data class GroupNameUpdated(val name: String) : CategoryGroupEvent
    data object ToggleGroupType : CategoryGroupEvent
    data object Save : CategoryGroupEvent
    data object CategoryGroupSaved : CategoryGroupEvent
}