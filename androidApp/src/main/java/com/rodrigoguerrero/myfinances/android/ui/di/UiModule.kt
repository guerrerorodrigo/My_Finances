package com.rodrigoguerrero.myfinances.android.ui.di

import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidAddCategoryGroupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { AndroidAddCategoryGroupViewModel(categoryRepository = get()) }
}
