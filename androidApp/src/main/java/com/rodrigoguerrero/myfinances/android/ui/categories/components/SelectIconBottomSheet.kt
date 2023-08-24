package com.rodrigoguerrero.myfinances.android.ui.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.models.categoryIcons
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.common.components.RoundedIcon
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme

@Composable
fun SelectIconBottomSheet(
    icons: List<ImageVector>,
    onIconSelected: (ImageVector) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.padding(all = AppTheme.padding.s),
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.icon_container_size)),
        contentPadding = PaddingValues(all = AppTheme.padding.m),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
    ) {
        items(icons) { icon -> RoundedIcon(icon = icon, onSelected = { onIconSelected(icon) }) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PhonePreviews
@Composable
private fun PreviewSelectIconBottomSheet() {
    MyApplicationTheme {
        val state = rememberModalBottomSheetState()
        LaunchedEffect(key1 = Unit) {
            state.expand()
        }
        SelectIconBottomSheet(
            icons = categoryIcons,
            onIconSelected = { },
        )
    }
}
