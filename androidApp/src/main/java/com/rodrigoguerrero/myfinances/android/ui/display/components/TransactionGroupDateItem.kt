package com.rodrigoguerrero.myfinances.android.ui.display.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.WidgetPreviews
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme
import com.rodrigoguerrero.myfinances.ui.transactioins.models.GroupDateItem

@Composable
fun TransactionGroupDateItem(
    groupDateItem: GroupDateItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(color = AppTheme.color.secondaryContainer)
            .padding(all = AppTheme.padding.m),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
    ) {
        Text(
            text = groupDateItem.date,
            modifier = Modifier.weight(1f),
            style = AppTheme.typography.labelLarge,
        )
        Text(
            text = groupDateItem.dayTotal,
            style = AppTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
        )
    }
}

@WidgetPreviews
@Composable
private fun PreviewTransactionGroupDateItem() {
    MyApplicationTheme {
        TransactionGroupDateItem(
            groupDateItem = GroupDateItem(
                date = "8 August 2023",
                dayTotal = "€641.00"
            ),
        )
    }
}
