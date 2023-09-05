package com.rodrigoguerrero.myfinances.ui.transactioins.models

import com.rodrigoguerrero.myfinances.ui.categories.models.Category

sealed interface AddTransactionEvent {
    data class NameUpdated(val value: String) : AddTransactionEvent
    data class AmountUpdated(val value: String) : AddTransactionEvent
    data class NotesUpdated(val value: String) : AddTransactionEvent
    data object NavigateBack : AddTransactionEvent
    data object ToggleTransactionType : AddTransactionEvent
    data object SaveTransaction : AddTransactionEvent
    data class OnCategoryUpdated(val category: Category) : AddTransactionEvent
    data object ShowDatePicker : AddTransactionEvent
    data object HideDatePicker : AddTransactionEvent
    data class OnDateSelected(val date: Long?) : AddTransactionEvent
    data object ShowTimePicker : AddTransactionEvent
    data object HideTimePicker : AddTransactionEvent
    data class OnTimeSelected(val hour: Int, val minute: Int) : AddTransactionEvent
}
