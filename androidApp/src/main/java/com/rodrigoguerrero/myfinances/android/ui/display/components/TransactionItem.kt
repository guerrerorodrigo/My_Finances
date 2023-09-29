package com.rodrigoguerrero.myfinances.android.ui.display.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.models.categoryIcons
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.WidgetPreviews
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.theme.Colors
import com.rodrigoguerrero.myfinances.ui.transactioins.models.TransactionItem

@Composable
fun TransactionItem(
    transactionItem: TransactionItem,
    modifier: Modifier = Modifier,
) {
    val color: Color = remember {
        if (transactionItem.type == TransactionType.INCOME) {
            Color(Colors.incomeColor)
        } else {
            Color(Colors.expenseColor)
        }
    }
    Row(
        modifier = modifier.padding(horizontal = AppTheme.padding.m),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = color)
                .size(size = dimensionResource(id = R.dimen.icon_size)),
        ) {
            transactionItem.iconPosition?.let {
                Icon(
                    imageVector = categoryIcons[it],
                    contentDescription = null,
                    modifier = Modifier.padding(all = AppTheme.padding.s),
                    tint = Color.White,
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AppTheme.padding.xxs),
        ) {
            Text(
                text = transactionItem.name,
                style = AppTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            )
            Text(text = transactionItem.category, style = AppTheme.typography.labelMedium)
        }
        Text(
            text = transactionItem.amount,
            style = AppTheme.typography.labelLarge.copy(
                color = color,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@WidgetPreviews
@Composable
private fun PreviewTransactionItem() {
    MyApplicationTheme {
        TransactionItem(
            transactionItem =
            TransactionItem(
                type = TransactionType.INCOME,
                name = "Sales Commission",
                category = "Bonus",
                amount = "€753.00",
                iconPosition = 2,
            )
        )
    }
}
