package com.rodrigoguerrero.myfinances.android.ui.di

import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidAddCategoryGroupViewModel
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidAddCategoryViewModel
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.CategoryCreationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { AndroidAddCategoryGroupViewModel(categoryRepository = get()) }
    viewModel { AndroidAddCategoryViewModel(categoryRepository = get(), isExpense = get()) }
    viewModel { CategoryCreationViewModel() }
}
