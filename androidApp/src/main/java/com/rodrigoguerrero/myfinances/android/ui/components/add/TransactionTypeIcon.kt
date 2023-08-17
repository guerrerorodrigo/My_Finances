package com.rodrigoguerrero.myfinances.android.ui.components.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.models.add.AddTransactionUiState
import com.rodrigoguerrero.myfinances.android.ui.models.display.TransactionType
import com.rodrigoguerrero.myfinances.android.ui.models.add.AddTransactionEvent
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme

@Composable
fun RowScope.TransactionTypeIcon(
    backgroundColor: Color,
    onEvent: (AddTransactionEvent) -> Unit,
    state: AddTransactionUiState
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = backgroundColor)
            .size(size = dimensionResource(id = R.dimen.add_transaction_type_icon_size))
            .clickable { onEvent(AddTransactionEvent.ToggleTransactionType) },
        contentAlignment = Alignment.Center,
    ) {
        this@TransactionTypeIcon.AnimatedVisibility(
            visible = state.type == TransactionType.INCOME,
            label = "income-anim",
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.padding(all = AppTheme.padding.xs),
                tint = Color.White,
            )
        }
        this@TransactionTypeIcon.AnimatedVisibility(
            visible = state.type == TransactionType.EXPENSE,
            label = "expense-anim",
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
                modifier = Modifier.padding(all = AppTheme.padding.xs),
                tint = Color.White,
            )
        }
    }
}
