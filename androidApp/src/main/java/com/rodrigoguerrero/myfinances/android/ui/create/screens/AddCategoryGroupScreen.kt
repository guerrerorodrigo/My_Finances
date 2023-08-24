package com.rodrigoguerrero.myfinances.android.ui.create.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.categories.components.AddCategoryTextField
import com.rodrigoguerrero.myfinances.android.ui.categories.components.CategoryTopBar
import com.rodrigoguerrero.myfinances.android.ui.categories.models.AddCategoryGroupUiState
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme

@Composable
fun AddCategoryGroupScreen(
    state: AddCategoryGroupUiState,
    onNameChanged: (String) -> Unit,
    onTypeSelected: (Boolean) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryTopBar(
                title = stringResource(id = R.string.add_new_category_group),
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
                value = state.group,
                onValueChange = onNameChanged,
                label = stringResource(R.string.category_group),
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier
                    .selectableGroup()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = state.isExpense,
                    onClick = { onTypeSelected(true) },
                )
                Text(
                    text = stringResource(R.string.expense),
                    style = AppTheme.typography.bodyLarge,
                )

                RadioButton(
                    selected = !state.isExpense,
                    onClick = { onTypeSelected(false) },
                    modifier = Modifier.padding(start = AppTheme.padding.m),
                )
                Text(
                    text = stringResource(R.string.income),
                    style = AppTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@PhonePreviews
@Composable
private fun PrivateAddNewCategoryScreen() {
    MyApplicationTheme {
        AddCategoryGroupScreen(
            state = AddCategoryGroupUiState(),
            onSave = {},
            onBack = {},
            onNameChanged = {},
            onTypeSelected = {},
        )
    }
}
