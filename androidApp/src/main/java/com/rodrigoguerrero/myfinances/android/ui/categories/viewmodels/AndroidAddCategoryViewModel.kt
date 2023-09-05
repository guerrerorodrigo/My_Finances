package com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import com.rodrigoguerrero.myfinances.ui.categories.viewmodels.AddCategoryViewModel
import com.rodrigoguerrero.myfinances.ui.categories.models.CreateCategoryEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class AndroidAddCategoryViewModel(
    private val categoryRepository: CategoryRepository,
    transactionType: TransactionType,
) : ViewModel() {
    private val viewModel by lazy {
        AddCategoryViewModel(
            coroutineScope = viewModelScope,
            categoryRepository = categoryRepository,
            transactionType = transactionType,
        )
    }

    val state = viewModel.state

    fun onEvent(event: CreateCategoryEvent) {
        viewModel.onEvent(event)
    }
}
