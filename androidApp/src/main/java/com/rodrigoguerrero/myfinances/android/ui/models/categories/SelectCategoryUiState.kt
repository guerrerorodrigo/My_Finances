package com.rodrigoguerrero.myfinances.android.ui.models.categories

import androidx.compose.runtime.Stable

@Stable
data class SelectCategoryUiState(
    val groupsWithCategories: Map<String, List<Category>>
)
