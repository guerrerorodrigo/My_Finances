package com.rodrigoguerrero.myfinances.android.ui.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.ui.categories.CategoryGroupUi

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SelectCategoryGroupDropDownMenu(
    isError: Boolean,
    error: String,
    selected: CategoryGroupUi?,
    items: List<CategoryGroupUi>,
    onCategorySelected: (CategoryGroupUi) -> Unit,
    onAddNewCategoryGroup: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isGroupMenuExpanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isGroupMenuExpanded,
        onExpandedChange = { isGroupMenuExpanded = !isGroupMenuExpanded }
    ) {
        AddCategoryTextField(
            value = selected?.name.orEmpty(),
            onValueChange = {},
            label = stringResource(R.string.group),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clickable { isGroupMenuExpanded = true },
            trailingIcon = {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            },
            isEnabled = false,
            isReadOnly = true,
            isError = isError,
            error = error,
        )
        ExposedDropdownMenu(
            modifier = Modifier.exposedDropdownSize(),
            expanded = isGroupMenuExpanded,
            onDismissRequest = { isGroupMenuExpanded = false },
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.name) },
                    onClick = {
                        onCategorySelected(selectionOption)
                        isGroupMenuExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
            DropdownMenuItem(
                text = { Text(stringResource(R.string.add_new_category_group)) },
                onClick = {
                    onAddNewCategoryGroup()
                    isGroupMenuExpanded = false
                },
                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                colors = MenuDefaults.itemColors(textColor = AppTheme.color.primary),
            )
        }
    }
}
