package com.rodrigoguerrero.myfinances.ui.transactioins.viewmodels

import com.rodrigoguerrero.myfinances.common.flows.CommonStateFlow
import com.rodrigoguerrero.myfinances.common.flows.toCommonStateFlow
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.transactions.models.Transaction
import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepository
import com.rodrigoguerrero.myfinances.ui.categories.models.toDomain
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionEvent
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionEvent.*
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTransactionViewModel(
    private val repository: TransactionRepository,
    coroutineScope: CoroutineScope?,
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(AddTransactionUiState())
    val state: CommonStateFlow<AddTransactionUiState> =
        _state
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = AddTransactionUiState(),
            ).toCommonStateFlow()


    fun onEvent(event: AddTransactionEvent) {
        when (event) {
            is AmountUpdated -> _state.update { it.copy(amount = event.value) }
            is NameUpdated -> _state.update { it.copy(name = event.value) }
            NavigateBack -> _state.update { it.copy(navigateBack = true) }
            is NotesUpdated -> _state.update { it.copy(notes = event.value) }
            SaveTransaction -> saveTransaction()
            ToggleTransactionType -> {
                _state.update {
                    it.copy(
                        type = if (it.type == TransactionType.INCOME) {
                            TransactionType.EXPENSE
                        } else {
                            TransactionType.INCOME
                        }
                    )
                }
            }

            is OnCategoryUpdated -> _state.update { it.copy(category = event.category) }
            ShowDatePicker -> _state.update { it.copy(showDatePicker = true) }
            HideDatePicker -> _state.update { it.copy(showDatePicker = false) }
            is OnDateSelected -> _state.update { it.copy(dateMillis = event.date) }
            HideTimePicker -> _state.update { it.copy(showTimePicker = false) }
            is OnTimeSelected -> _state.update {
                it.copy(
                    hour = event.hour,
                    minutes = event.minute
                )
            }

            ShowTimePicker -> _state.update { it.copy(showTimePicker = true) }
        }
    }

    private fun saveTransaction() {
        viewModelScope.launch {
            with(_state.value) {
                repository.createTransaction(
                    transaction = Transaction(
                        id = 0,
                        name = name,
                        type = type,
                        amount = if (amount.isEmpty()) {
                            0.0
                        } else if (type == TransactionType.EXPENSE && amount.first() != '-') {
                            "-${amount}".toDouble()
                        } else {
                            amount.toDouble()
                        },
                        date = dateMillis ?: 0,
                        time = time,
                        notes = notes,
                        category = category.toDomain(),
                    )
                )
                onEvent(event = NavigateBack)
            }
        }
    }
}