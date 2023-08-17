package com.rodrigoguerrero.myfinances.android.ui.models.display

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
data class TransactionItem(
    val type: TransactionType,
    val name: String,
    val category: String,
    val amount: String,
    val icon: ImageVector,
)
