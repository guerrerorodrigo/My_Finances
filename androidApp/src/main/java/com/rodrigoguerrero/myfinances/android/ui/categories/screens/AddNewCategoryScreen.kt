package com.rodrigoguerrero.myfinances.android.ui.categories.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.components.AddCategoryTextField
import com.rodrigoguerrero.myfinances.android.ui.categories.components.CategoryTopBar
import com.rodrigoguerrero.myfinances.android.ui.categories.components.SelectCategoryGroupDropDownMenu
import com.rodrigoguerrero.myfinances.android.ui.categories.models.categoryIcons
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.AndroidAddCategoryViewModel
import com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels.CategoryCreationViewModel
import com.rodrigoguerrero.myfinances.android.ui.common.components.RoundedIcon
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.ui.categories.CategoryEvent
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AddNewCategoryScreen(
    isExpense: Boolean,
    onAddNewCategoryGroup: () -> Unit,
    onChangeIcon: () -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AndroidAddCategoryViewModel = koinViewModel(
        parameters = { parametersOf(isExpense) }
    ),
    viewModelStoreOwner: ViewModelStoreOwner,
    sharedViewModel: CategoryCreationViewModel = koinNavViewModel(viewModelStoreOwner = viewModelStoreOwner),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sharedState by sharedViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryTopBar(
                title = stringResource(
                    if (isExpense) {
                        R.string.new_expense_category
                    } else {
                        R.string.new_income_category
                    }
                ),
                onBack = onBack,
                onSave = onSave,
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
                onValueChange = { viewModel.onEvent(CategoryEvent.OnNameUpdated(it)) },
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
                RoundedIcon(icon = sharedState.iconPosition?.let { categoryIcons[it] })
            }
            SelectCategoryGroupDropDownMenu(
                items = state.groups,
                selected = state.selectedGroup,
                onCategorySelected = { viewModel.onEvent(CategoryEvent.OnGroupSelected(it)) },
                onAddNewCategoryGroup = onAddNewCategoryGroup,
            )
        }
    }
}