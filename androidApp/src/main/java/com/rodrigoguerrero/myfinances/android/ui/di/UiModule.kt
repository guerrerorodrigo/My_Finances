package com.rodrigoguerrero.myfinances.android.ui.di

import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidAddCategoryGroupViewModel
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidAddCategoryViewModel
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidCategoriesViewModel
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.CategoryCreationViewModel
import com.rodrigoguerrero.myfinances.android.ui.create.viewmodels.AndroidAddTransactionViewModel
import com.rodrigoguerrero.myfinances.android.ui.create.viewmodels.TransactionCreationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { AndroidAddCategoryGroupViewModel(categoryRepository = get()) }
    viewModel { AndroidAddCategoryViewModel(categoryRepository = get(), transactionType = get()) }
    viewModel { CategoryCreationViewModel() }
    viewModel { TransactionCreationViewModel() }
    viewModel { AndroidCategoriesViewModel(categoryRepository = get()) }
    viewModel { AndroidAddTransactionViewModel(transactionRepository = get()) }
}
