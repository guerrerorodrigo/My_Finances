package com.rodrigoguerrero.myfinances.ui.categories

import com.rodrigoguerrero.myfinances.common.flows.CommonStateFlow
import com.rodrigoguerrero.myfinances.common.flows.toCommonStateFlow
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddCategoryGroupViewModel(
    private val categoryRepository: CategoryRepository,
    coroutineScope: CoroutineScope?,
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(AddCategoryGroupUiState())
    val state: CommonStateFlow<AddCategoryGroupUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AddCategoryGroupUiState(),
        )
        .toCommonStateFlow()

    fun onEvent(event: CategoryGroupEvent) {
        when (event) {
            is CategoryGroupEvent.GroupNameUpdated -> _state.update { it.copy(group = event.name) }
            CategoryGroupEvent.Save -> with(_state.value) {
                saveCategoryGroup(name = group, isExpense = isComplete)
            }

            CategoryGroupEvent.ToggleGroupType -> {
                _state.update { it.copy(isComplete = !_state.value.isComplete) }
            }

            CategoryGroupEvent.CategoryGroupSaved -> {
                _state.update { it.copy(navigateBack = true) }
            }
        }
    }

    private fun saveCategoryGroup(name: String, isExpense: Boolean) {
        viewModelScope.launch {
            categoryRepository.addCategoryGroup(
                name = name,
                transactionType = if (isExpense) {
                    TransactionType.EXPENSE
                } else {
                    TransactionType.INCOME
                },
            )
            onEvent(CategoryGroupEvent.CategoryGroupSaved)
        }
    }
}
