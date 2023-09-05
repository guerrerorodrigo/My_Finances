package com.rodrigoguerrero.myfinances.ui.categories.viewmodels

import com.rodrigoguerrero.myfinances.common.flows.CommonStateFlow
import com.rodrigoguerrero.myfinances.common.flows.toCommonStateFlow
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import com.rodrigoguerrero.myfinances.ui.categories.models.AddCategoryUiState
import com.rodrigoguerrero.myfinances.ui.categories.models.CreateCategoryEvent
import com.rodrigoguerrero.myfinances.ui.categories.models.toUi
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
    transactionType: TransactionType,
    coroutineScope: CoroutineScope?,
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(AddCategoryUiState())
    val state: CommonStateFlow<AddCategoryUiState> = combine(
        _state,
        categoryRepository.getCategoryGroups(
            transactionType = transactionType,
        ),
    ) { state, groups ->
        state.copy(groups = groups.map { it.toUi() })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AddCategoryUiState(transactionType = transactionType),
    ).toCommonStateFlow()

    fun onEvent(event: CreateCategoryEvent) {
        when (event) {
            is CreateCategoryEvent.OnGroupSelected -> {
                _state.update { it.copy(selectedGroup = event.group) }
            }

            is CreateCategoryEvent.OnNameUpdated -> _state.update { it.copy(name = event.value) }
            CreateCategoryEvent.OnToggleTransactionType -> {
                _state.update {
                    it.copy(
                        transactionType = if (_state.value.transactionType == TransactionType.EXPENSE) {
                            TransactionType.INCOME
                        } else {
                            TransactionType.EXPENSE
                        }
                    )
                }
            }

            CreateCategoryEvent.Save -> saveCategory()
            CreateCategoryEvent.Validate -> validate()
            is CreateCategoryEvent.UpdateIconPosition -> {
                _state.update { it.copy(iconItemPosition = event.position) }
            }
        }
    }

    private fun validate() {
        _state.update { it.copy(isNameEmpty = _state.value.name.isEmpty()) }
        _state.update { it.copy(isGroupSelected = _state.value.selectedGroup != null) }

        if (!_state.value.hasErrors()) {
            onEvent(CreateCategoryEvent.Save)
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
                        groupName = selectedGroup.name,
                    )
                    _state.update { it.copy(navigateBack = true) }
                }
            }
        }
    }
}
