package com.rodrigoguerrero.myfinances.android.ui.models.categories

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
data class AddCategoryUiState(
    val title: String = "",
    val name: String = "",
    val icon: ImageVector? = null,
    val groups: List<String> = emptyList(),
    val selectedGroup: String = "",
)
