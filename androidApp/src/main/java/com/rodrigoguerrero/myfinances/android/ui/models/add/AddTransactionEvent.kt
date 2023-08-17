package com.rodrigoguerrero.myfinances.android.ui.models.add

sealed interface AddTransactionEvent {
    data class NameUpdated(val value: String) : AddTransactionEvent
    data class AmountUpdated(val value: String) : AddTransactionEvent
    data class NotesUpdated(val value: String) : AddTransactionEvent
    object NavigateBack : AddTransactionEvent
    object ToggleTransactionType : AddTransactionEvent
    object ShowCalendar : AddTransactionEvent
    object ShowTimePicker : AddTransactionEvent
    object ShowCategoryPicker : AddTransactionEvent
    object SaveTransaction : AddTransactionEvent
}
