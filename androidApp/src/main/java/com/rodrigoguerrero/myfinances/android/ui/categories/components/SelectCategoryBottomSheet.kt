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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.models.Category
import com.rodrigoguerrero.myfinances.android.ui.categories.models.SelectCategoryUiState
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.common.components.RoundedIcon
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme

private const val NUMBER_OF_COLUMNS = 5

@Composable
fun SelectCategoryBottomSheet(
    selectCategoryUiState: SelectCategoryUiState,
    onSelected: (Category) -> Unit,
    onAddNewCategory: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.padding(all = AppTheme.padding.s),
        columns = GridCells.Fixed(NUMBER_OF_COLUMNS),
        contentPadding = PaddingValues(all = AppTheme.padding.m),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
    ) {
        selectCategoryUiState.groupsWithCategories.keys.forEach { key ->
            item(span = { GridItemSpan(NUMBER_OF_COLUMNS) }) {
                Text(text = key, style = AppTheme.typography.titleMedium)
            }
            selectCategoryUiState.groupsWithCategories[key]?.let { categories ->
                items(categories) { category ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.s),
                        modifier = Modifier.clickable { onSelected(category) }
                    ) {
                        RoundedIcon(icon = category.icon)
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
                    .clickable { onAddNewCategory(true) }, // TODO: pass correct value
            )
        }
        item(span = { GridItemSpan(NUMBER_OF_COLUMNS) }) {
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PhonePreviews
@Composable
private fun PreviewSelectCategoryBottomSheet() {
    MyApplicationTheme {
        val category = Category(name = "Fuel", icon = Icons.Filled.Save)
        val items = linkedMapOf(
            "Car" to listOf(category, category, category),
            "Entertainment" to listOf(category),
            "Household" to listOf(category, category, category, category, category)
        )
        val state = rememberModalBottomSheetState()
        LaunchedEffect(key1 = Unit) {
            state.expand()
        }
        SelectCategoryBottomSheet(
            onSelected = { },
            selectCategoryUiState = SelectCategoryUiState(
                groupsWithCategories = items,
            ),
            onAddNewCategory = { },
        )
    }
}
