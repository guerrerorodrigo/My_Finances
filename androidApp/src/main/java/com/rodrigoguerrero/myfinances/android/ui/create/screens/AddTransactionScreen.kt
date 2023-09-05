package com.rodrigoguerrero.myfinances.android.ui.create.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.models.categoryIcons
import com.rodrigoguerrero.myfinances.android.ui.common.components.TransactionTextField
import com.rodrigoguerrero.myfinances.android.ui.create.components.AddTransactionScreenTopAppBar
import com.rodrigoguerrero.myfinances.android.ui.create.components.SaveTransactionFab
import com.rodrigoguerrero.myfinances.android.ui.create.components.TransactionAmountTextField
import com.rodrigoguerrero.myfinances.android.ui.create.viewmodels.AndroidAddTransactionViewModel
import com.rodrigoguerrero.myfinances.android.ui.create.viewmodels.TransactionCreationViewModel
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.theme.Colors
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionEvent
import org.koin.androidx.compose.navigation.koinNavViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTransactionScreen(
    onBack: () -> Unit,
    onShowCategoryPicker: () -> Unit,
    viewModelStoreOwner: ViewModelStoreOwner,
    modifier: Modifier = Modifier,
    sharedViewModel: TransactionCreationViewModel = koinNavViewModel(viewModelStoreOwner = viewModelStoreOwner),
    viewModel: AndroidAddTransactionViewModel = koinNavViewModel(),
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sharedState by sharedViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = sharedState.selectedCategory) {
        sharedState.selectedCategory?.let {
            viewModel.onEvent(AddTransactionEvent.OnCategoryUpdated(it))
        }
    }

    LaunchedEffect(key1 = state.type) {
        sharedViewModel.setTransactionType(state.type)
    }

    LaunchedEffect(key1 = state.navigateBack) {
        if (state.navigateBack) { onBack() }
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (state.type == TransactionType.INCOME) {
            Color(Colors.incomeColor)
        } else {
            Color(Colors.expenseColor)
        },
        label = "background-color",
        animationSpec = tween(300, easing = LinearEasing),
    )
    Scaffold(
        modifier = modifier,
        topBar = { AddTransactionScreenTopAppBar(onBack = onBack) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            SaveTransactionFab(onClick = { viewModel.onEvent(AddTransactionEvent.SaveTransaction) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(state = rememberScrollState()),
        ) {
            TransactionTextField(
                text = state.name,
                onValueChange = { viewModel.onEvent(AddTransactionEvent.NameUpdated(it)) },
                placeholder = stringResource(R.string.transaction_name),
                leadingIcon = Icons.Outlined.Description,
                modifier = Modifier.fillMaxWidth()
            )

            TransactionAmountTextField(
                backgroundColor = backgroundColor,
                state = state,
                onAmountUpdated = { viewModel.onEvent(AddTransactionEvent.AmountUpdated(it)) },
                toggleTransactionType = { viewModel.onEvent(AddTransactionEvent.ToggleTransactionType) }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
            ) {
                TransactionTextField(
                    text = state.date,
                    leadingIcon = Icons.Outlined.CalendarMonth,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
//                            onEvent(AddTransactionEvent.ShowCalendar)
                        },
                    placeholder = "",
                    onValueChange = { },
                    isReadOnly = true,
                    isEnabled = false,
                )
                TransactionTextField(
                    text = state.time,
                    leadingIcon = Icons.Outlined.AccessTime,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
//                            onEvent(AddTransactionEvent.ShowTimePicker)
                        },
                    placeholder = "",
                    onValueChange = { },
                    isReadOnly = true,
                    isEnabled = false,
                )
            }
            TransactionTextField(
                text = state.category.name,
                onValueChange = {},
                leadingIcon = state.category.iconPosition?.let { categoryIcons[it] }
                    ?: Icons.Outlined.Category,
                label = {
                    Text(
                        text = state.category.group,
                        style = AppTheme.typography.labelSmall,
                    )
                },
                placeholder = "",
                isReadOnly = true,
                isEnabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        keyboardController?.hide()
                        onShowCategoryPicker()
                    },
            )
            TransactionTextField(
                text = state.notes,
                onValueChange = { viewModel.onEvent(AddTransactionEvent.NotesUpdated(it)) },
                leadingIcon = Icons.Outlined.StickyNote2,
                placeholder = stringResource(R.string.notes),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
            )
        }
    }
}
