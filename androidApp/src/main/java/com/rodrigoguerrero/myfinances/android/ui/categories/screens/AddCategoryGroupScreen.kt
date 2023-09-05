package com.rodrigoguerrero.myfinances.android.ui.categories.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.components.AddCategoryTextField
import com.rodrigoguerrero.myfinances.android.ui.categories.components.CategoryTopBar
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidAddCategoryGroupViewModel
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme
import com.rodrigoguerrero.myfinances.ui.categories.models.CategoryGroupEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddCategoryGroupScreen(
    onBack: () -> Unit,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AndroidAddCategoryGroupViewModel = koinViewModel(),
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.navigateBack) {
        if (state.navigateBack) {
            keyboardController?.hide()
            onComplete()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryTopBar(
                title = stringResource(id = R.string.add_new_category_group),
                onBack = onBack,
                onSave = { viewModel.onEvent(CategoryGroupEvent.Validate) },
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
                onValueChange = { viewModel.onEvent(CategoryGroupEvent.GroupNameUpdated(it)) },
                label = stringResource(R.string.category_group),
                modifier = Modifier.fillMaxWidth(),
                isError = state.isNameEmpty,
                error = if (state.isNameEmpty) {
                    stringResource(id = R.string.error_empty_category_group_name)
                } else {
                    ""
                },
            )
            Row(
                modifier = Modifier
                    .selectableGroup()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = state.isComplete,
                    onClick = { viewModel.onEvent(CategoryGroupEvent.ToggleGroupType) },
                )
                Text(
                    text = stringResource(R.string.expense),
                    style = AppTheme.typography.bodyLarge,
                )

                RadioButton(
                    selected = !state.isComplete,
                    onClick = { viewModel.onEvent(CategoryGroupEvent.ToggleGroupType) },
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
            onBack = {},
            onComplete = {},
        )
    }
}
