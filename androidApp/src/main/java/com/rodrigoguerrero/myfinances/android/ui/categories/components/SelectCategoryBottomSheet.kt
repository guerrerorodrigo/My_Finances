package com.rodrigoguerrero.myfinances.android.ui.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.models.categoryIcons
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidCategoriesViewModel
import com.rodrigoguerrero.myfinances.android.ui.common.components.RoundedIcon
import com.rodrigoguerrero.myfinances.android.ui.create.viewmodels.TransactionCreationViewModel
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.categories.models.SelectCategoryEvent
import com.rodrigoguerrero.myfinances.ui.categories.models.SelectCategoryEvent.OnCategorySelected
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel

private const val NUMBER_OF_COLUMNS = 5

@Composable
fun SelectCategoryBottomSheet(
    onAddNewCategory: (TransactionType) -> Unit,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AndroidCategoriesViewModel = koinViewModel(),
    viewModelStoreOwner: ViewModelStoreOwner,
    sharedViewModel: TransactionCreationViewModel = koinNavViewModel(viewModelStoreOwner = viewModelStoreOwner),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sharedState by sharedViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.selectedCategory) {
        state.selectedCategory?.let {
            sharedViewModel.onCategorySelected(it)
            onSelected()
        }
    }

    LaunchedEffect(key1 = sharedState.transactionType) {
        viewModel.onEvent(SelectCategoryEvent.UpdateTransactionType(sharedState.transactionType))
    }

    LazyVerticalGrid(
        modifier = modifier.padding(all = AppTheme.padding.s),
        columns = GridCells.Fixed(NUMBER_OF_COLUMNS),
        contentPadding = PaddingValues(all = AppTheme.padding.m),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
    ) {
        state.groupsWithCategories.keys.forEach { key ->
            item(span = { GridItemSpan(NUMBER_OF_COLUMNS) }) {
                Text(text = key, style = AppTheme.typography.titleMedium)
            }
            state.groupsWithCategories[key]?.let { categories ->
                items(categories) { category ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.s),
                        modifier = Modifier.clickable {
                            viewModel.onEvent(OnCategorySelected(category))
                        }
                    ) {
                        RoundedIcon(icon = category.iconPosition?.let { categoryIcons[it] })
                        Text(text = category.name, style = AppTheme.typography.labelLarge)
                    }
                }
            }
        }
        item(span = { GridItemSpan(NUMBER_OF_COLUMNS) }) {
            Text(
                text = stringResource(R.string.add_new_category),
                style = AppTheme.typography.titleMedium,
                color = AppTheme.color.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAddNewCategory(sharedState.transactionType) },
            )
        }
        item(span = { GridItemSpan(NUMBER_OF_COLUMNS) }) {
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}
