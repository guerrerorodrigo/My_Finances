package com.rodrigoguerrero.myfinances.android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.common.components.RoundedIcon
import com.rodrigoguerrero.myfinances.android.ui.components.categories.AddCategoryTextField
import com.rodrigoguerrero.myfinances.android.ui.components.categories.CategoryTopBar
import com.rodrigoguerrero.myfinances.android.ui.components.categories.SelectCategoryGroupDropDownMenu
import com.rodrigoguerrero.myfinances.android.ui.models.categories.AddCategoryUiState
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme

@Composable
fun AddNewCategoryScreen(
    state: AddCategoryUiState,
    onAddNewCategory: () -> Unit,
    onCategorySelected: (String) -> Unit,
    onChangeIcon: () -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryTopBar(
                title = state.title,
                onBack = onBack,
                onSave = { onSave() },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = AppTheme.padding.m),
            verticalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
        ) {
            AddCategoryTextField(
                value = state.name,
                onValueChange = {},
                label = stringResource(R.string.category_name),
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChangeIcon() },
                horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AddCategoryTextField(
                    value = "",
                    onValueChange = {},
                    label = stringResource(R.string.select_icon),
                    modifier = Modifier.weight(1f),
                    isReadOnly = true,
                    isEnabled = false,
                )
                RoundedIcon(icon = state.icon)
            }
            SelectCategoryGroupDropDownMenu(
                items = state.groups,
                selected = state.selectedGroup,
                onCategorySelected = onCategorySelected,
                onAddNewCategory = onAddNewCategory,
            )
        }
    }
}

@PhonePreviews
@Composable
private fun PrivateAddNewCategoryScreen() {
    MyApplicationTheme {
        AddNewCategoryScreen(
            state = AddCategoryUiState(
                title = "New Expense Category",
                selectedGroup = "Car",
                groups = listOf("Car", "Entertainment", "Household", "Utilities", "Others"),
                icon = Icons.Filled.School,
            ),
            onAddNewCategory = {},
            onCategorySelected = {},
            onSave = {},
            onBack = {},
            onChangeIcon = {},
        )
    }
}
