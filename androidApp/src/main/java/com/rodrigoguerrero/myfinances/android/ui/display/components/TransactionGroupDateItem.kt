package com.rodrigoguerrero.myfinances.android.ui.display.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.WidgetPreviews
import com.rodrigoguerrero.myfinances.android.ui.display.models.GroupDateItem
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme

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
            text = groupDateItem.day,
            style = AppTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.width(width = dimensionResource(id = R.dimen.icon_size)),
            textAlign = TextAlign.Center,
        )
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
                day = "TUE",
                date = "8 August 2023",
                dayTotal = "€641.00"
            ),
        )
    }
}
