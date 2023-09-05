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
import kotlinx.coroutines.launch

class AddCategoryViewModel(
    private val categoryRepository: CategoryRepository,
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
        state.copy(groups = groups.map { it.toUi() })
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

            CategoryEvent.Save -> saveCategory()
            CategoryEvent.Validate -> validate()
        }
    }

    private fun validate() {
        _state.update { it.copy(isNameEmpty = _state.value.name.isEmpty()) }
        _state.update { it.copy(isGroupSelected = _state.value.selectedGroup != null) }

        if (!_state.value.hasErrors()) {
            onEvent(CategoryEvent.Save)
        }
    }

    private fun saveCategory() {
        viewModelScope.launch {
            with(_state.value) {
                selectedGroup?.id?.let { groupId ->
                    categoryRepository.addCategory(
                        name = name,
                        groupId = groupId,
                        iconPosition = iconItemPosition,
                    )
                    _state.update { it.copy(navigateBack = true) }
                }
            }
        }
    }
}
