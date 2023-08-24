package com.rodrigoguerrero.myfinances.android.ui.display.models

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
