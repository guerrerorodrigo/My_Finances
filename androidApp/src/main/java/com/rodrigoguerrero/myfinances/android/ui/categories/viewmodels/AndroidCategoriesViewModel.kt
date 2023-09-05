package com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels

import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import com.rodrigoguerrero.myfinances.ui.categories.models.SelectCategoryEvent
import com.rodrigoguerrero.myfinances.ui.categories.viewmodels.CategoriesViewModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class AndroidCategoriesViewModel(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    private val viewModel by lazy {
        CategoriesViewModel(
            coroutineScope = viewModelScope,
            categoryRepository = categoryRepository,
        )
    }

    val state = viewModel.state

    fun onEvent(event: SelectCategoryEvent) {
        viewModel.onEvent(event)
    }
}
