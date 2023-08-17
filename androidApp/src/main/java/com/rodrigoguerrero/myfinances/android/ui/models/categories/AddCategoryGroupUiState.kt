package com.rodrigoguerrero.myfinances.android.ui.models.categories

import androidx.compose.runtime.Stable

@Stable
data class AddCategoryGroupUiState(
    val group: String = "",
    val isExpense: Boolean = true,
)
