package com.rodrigoguerrero.myfinances.ui.categories.viewmodels

import com.rodrigoguerrero.myfinances.common.flows.CommonStateFlow
import com.rodrigoguerrero.myfinances.common.flows.toCommonStateFlow
import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import com.rodrigoguerrero.myfinances.ui.categories.models.Category
import com.rodrigoguerrero.myfinances.ui.categories.models.SelectCategoryEvent
import com.rodrigoguerrero.myfinances.ui.categories.models.SelectCategoryUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CategoriesViewModel(
    categoryRepository: CategoryRepository,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(SelectCategoryUiState())
    val state: CommonStateFlow<SelectCategoryUiState> =
        combine(
            _state,
            categoryRepository.getCategories(transactionType = _state.value.type),
        ) { state, categories ->
            state.copy(
                groupsWithCategories = categories
                    .groupBy { it.group.name }
                    .mapValues { categoriesMap ->
                        categoriesMap.value.map {
                            Category(
                                name = it.name,
                                iconPosition = it.iconPosition,
                                id = it.id.toInt(),
                                group = it.group.name,
                                groupId = it.group.id,
                            )
                        }
                    }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SelectCategoryUiState(),
        ).toCommonStateFlow()

    fun onEvent(event: SelectCategoryEvent) {
        when (event) {
            is SelectCategoryEvent.OnCategorySelected -> {
                _state.update { it.copy(selectedCategory = event.category) }
            }

            is SelectCategoryEvent.UpdateTransactionType -> {
                _state.update { it.copy(type = event.type) }
            }
        }
    }
}
