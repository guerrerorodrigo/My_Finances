package com.rodrigoguerrero.myfinances.android.ui.categories.models

import androidx.compose.runtime.Stable

@Stable
data class SelectCategoryUiState(
    val groupsWithCategories: Map<String, List<Category>> = mapOf(),
)
