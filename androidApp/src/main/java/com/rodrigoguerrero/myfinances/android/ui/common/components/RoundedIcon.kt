package com.rodrigoguerrero.myfinances.android.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.WidgetPreviews
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme

@Composable
fun RoundedIcon(
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    onSelected: (() -> Unit)? = null,
) {
    val itemModifier = modifier
        .size(dimensionResource(id = R.dimen.icon_container_size))
        .clip(CircleShape)
        .background(color = AppTheme.color.primary)

    onSelected?.let { itemModifier.clickable { onSelected.invoke() } }
    Box(
        modifier = itemModifier,
        contentAlignment = Alignment.Center,
    ) {
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppTheme.color.onPrimary,
                modifier = Modifier.padding(AppTheme.padding.xs),
            )
        }
    }
}

@WidgetPreviews
@Composable
private fun PreviewNullRoundedIcon() {
    MyApplicationTheme {
        RoundedIcon(icon = null)
    }
}

@WidgetPreviews
@Composable
private fun PreviewRoundedIcon() {
    MyApplicationTheme {
        RoundedIcon(icon = Icons.Filled.School)
    }
}
