package com.rodrigoguerrero.myfinances.ui.categories

import com.rodrigoguerrero.myfinances.common.flows.CommonStateFlow
import com.rodrigoguerrero.myfinances.common.flows.toCommonStateFlow
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AddCategoryViewModel(
    categoryRepository: CategoryRepository,
    isExpense: Boolean,
    coroutineScope: CoroutineScope?,
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(AddCategoryUiState())
    val state: CommonStateFlow<AddCategoryUiState> = combine(
        _state,
        categoryRepository.getCategoryGroups(
            transactionType = if (isExpense) {
                TransactionType.EXPENSE
            } else {
                TransactionType.INCOME
            }
        ),
    ) { state, groups ->
        state.copy(groups = groups.map { it.name })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AddCategoryUiState(isExpense = isExpense),
    ).toCommonStateFlow()

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.OnGroupSelected -> {
                _state.update { it.copy(selectedGroup = event.group) }
            }
            is CategoryEvent.OnNameUpdated -> _state.update { it.copy(name = event.value) }
            CategoryEvent.OnToggleTransactionType -> {
                _state.update { it.copy(isExpense = !_state.value.isExpense) }
            }
        }
    }
}
