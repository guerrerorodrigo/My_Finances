package com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels

import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import com.rodrigoguerrero.myfinances.ui.categories.AddCategoryViewModel
import com.rodrigoguerrero.myfinances.ui.categories.CategoryEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class AndroidAddCategoryViewModel(
    private val categoryRepository: CategoryRepository,
    isExpense: Boolean,
) : ViewModel() {
    private val viewModel by lazy {
        AddCategoryViewModel(
            coroutineScope = viewModelScope,
            categoryRepository = categoryRepository,
            isExpense = isExpense,
        )
    }

    val state = viewModel.state

    fun onEvent(event: CategoryEvent) {
        viewModel.onEvent(event)
    }
}
