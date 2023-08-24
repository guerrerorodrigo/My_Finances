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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.android.ui.categories.models.Category
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.common.components.TransactionTextField
import com.rodrigoguerrero.myfinances.android.ui.create.components.AddTransactionScreenTopAppBar
import com.rodrigoguerrero.myfinances.android.ui.create.components.SaveTransactionFab
import com.rodrigoguerrero.myfinances.android.ui.create.components.TransactionAmountTextField
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionUiState
import com.rodrigoguerrero.myfinances.android.ui.display.models.TransactionType
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionEvent
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme
import com.rodrigoguerrero.myfinances.ui.theme.Colors

@Composable
fun AddTransactionScreen(
    state: AddTransactionUiState,
    onEvent: (AddTransactionEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
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
        topBar = { AddTransactionScreenTopAppBar(onEvent) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            SaveTransactionFab(onClick = { onEvent(AddTransactionEvent.SaveTransaction) })
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
                onValueChange = { onEvent(AddTransactionEvent.NameUpdated(it)) },
                placeholder = stringResource(R.string.transaction_name),
                leadingIcon = Icons.Outlined.Description,
                modifier = Modifier.fillMaxWidth()
            )

            TransactionAmountTextField(backgroundColor, onEvent, state)
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
                        .clickable { onEvent(AddTransactionEvent.ShowCalendar) },
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
                        .clickable { onEvent(AddTransactionEvent.ShowTimePicker) },
                    placeholder = "",
                    onValueChange = { },
                    isReadOnly = true,
                    isEnabled = false,
                )
            }
            TransactionTextField(
                text = state.category.name,
                onValueChange = {},
                leadingIcon = Icons.Outlined.Category,
                label = {
                    Text(
                        text = state.categoryGroup,
                        style = AppTheme.typography.labelSmall,
                    )
                },
                placeholder = "",
                isReadOnly = true,
                isEnabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onEvent(AddTransactionEvent.ShowCategoryPicker) },
            )
            TransactionTextField(
                text = state.notes,
                onValueChange = { onEvent(AddTransactionEvent.NotesUpdated(it)) },
                leadingIcon = Icons.Outlined.StickyNote2,
                placeholder = stringResource(R.string.notes),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
            )
        }
    }
}

@PhonePreviews
@Composable
private fun PreviewAddTransactionScreen() {
    MyApplicationTheme {
        AddTransactionScreen(
            onEvent = { },
            state = AddTransactionUiState(
                date = "8 August 2023",
                time = "15:34",
                category = Category("Category Name", null),
                categoryGroup = "Category Group"
            ),
        )
    }
}
