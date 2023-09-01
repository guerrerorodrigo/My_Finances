package com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels

import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import com.rodrigoguerrero.myfinances.ui.categories.AddCategoryGroupViewModel
import com.rodrigoguerrero.myfinances.ui.categories.CategoryGroupEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class AndroidAddCategoryGroupViewModel(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    private val viewModel by lazy {
        AddCategoryGroupViewModel(
            coroutineScope = viewModelScope,
            categoryRepository = categoryRepository,
        )
    }

    val state = viewModel.state

    fun onEvent(event: CategoryGroupEvent) {
        viewModel.onEvent(event)
    }
}
