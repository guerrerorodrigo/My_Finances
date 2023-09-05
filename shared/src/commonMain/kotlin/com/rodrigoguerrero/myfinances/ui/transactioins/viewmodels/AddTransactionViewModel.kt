package com.rodrigoguerrero.myfinances.ui.transactioins.viewmodels

import com.rodrigoguerrero.myfinances.common.flows.CommonStateFlow
import com.rodrigoguerrero.myfinances.common.flows.toCommonStateFlow
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepository
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionEvent
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

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
            is AddTransactionEvent.AmountUpdated -> {
                _state.update { it.copy(amount = event.value) }
            }

            is AddTransactionEvent.NameUpdated -> {
                _state.update { it.copy(name = event.value) }
            }

            AddTransactionEvent.NavigateBack -> {
                _state.update { it.copy(navigateBack = true) }
            }
            is AddTransactionEvent.NotesUpdated -> {
                _state.update { it.copy(notes = event.value) }
            }

            AddTransactionEvent.SaveTransaction -> TODO()
            AddTransactionEvent.ToggleTransactionType -> {
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

            is AddTransactionEvent.OnCategoryUpdated -> {
                _state.update { it.copy(category = event.category) }
            }
        }
    }
}