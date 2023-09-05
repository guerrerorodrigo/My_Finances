package com.rodrigoguerrero.myfinances.android.ui.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.ViewModelStoreOwner
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.CategoryCreationViewModel
import com.rodrigoguerrero.myfinances.android.ui.common.components.RoundedIcon
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun SelectIconBottomSheet(
    icons: List<ImageVector>,
    onIconSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner,
    sharedViewModel: CategoryCreationViewModel = koinNavViewModel(viewModelStoreOwner = viewModelStoreOwner),
) {
    LazyVerticalGrid(
        modifier = modifier.padding(all = AppTheme.padding.s),
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.icon_container_size)),
        contentPadding = PaddingValues(all = AppTheme.padding.m),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
    ) {
        itemsIndexed(icons) { index, icon ->
            RoundedIcon(
                icon = icon,
                onSelected = {
                    sharedViewModel.onIconPositionSelected(index)
                    onIconSelected(index)
                },
            )
        }
    }
}
