package com.rodrigoguerrero.myfinances.android.ui.create.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.common.components.TransactionTextField
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionUiState

@Composable
fun TransactionAmountTextField(
    backgroundColor: Color,
    toggleTransactionType: () -> Unit,
    onAmountUpdated: (String) -> Unit,
    state: AddTransactionUiState
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.xs),
        modifier = Modifier.padding(start = AppTheme.padding.sm),
    ) {
        TransactionTypeIcon(backgroundColor, toggleTransactionType, state)
        TransactionTextField(
            text = state.amount,
            onValueChange = { onAmountUpdated(it) },
            placeholder = stringResource(R.string.empty_amount),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            prefix = if (state.type == TransactionType.EXPENSE) {
                stringResource(R.string.minus_sign)
            } else {
                null
            }
        )
    }
}
