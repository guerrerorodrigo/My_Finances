package com.rodrigoguerrero.myfinances.ui.categories.models

sealed interface CategoryGroupEvent {
    data class GroupNameUpdated(val name: String) : CategoryGroupEvent
    data object ToggleGroupType : CategoryGroupEvent
    data object Validate : CategoryGroupEvent
    data object Save : CategoryGroupEvent
    data object CategoryGroupSaved : CategoryGroupEvent
}
