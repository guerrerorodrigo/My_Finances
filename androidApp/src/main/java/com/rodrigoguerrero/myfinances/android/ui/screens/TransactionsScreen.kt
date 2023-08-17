package com.rodrigoguerrero.myfinances.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.outlined.Dining
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.components.display.TransactionGroupDateItem
import com.rodrigoguerrero.myfinances.android.ui.components.display.TransactionItem
import com.rodrigoguerrero.myfinances.android.ui.models.display.GroupDateItem
import com.rodrigoguerrero.myfinances.android.ui.models.display.TransactionItem
import com.rodrigoguerrero.myfinances.android.ui.models.display.TransactionListUiState
import com.rodrigoguerrero.myfinances.android.ui.models.display.TransactionType
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme

@Composable
fun TransactionsScreen(
    state: TransactionListUiState,
    onAddTransaction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            AddNewTransactionFab(onClick = onAddTransaction)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
            modifier = Modifier.padding(paddingValues),
        ) {
            state.transactionsByDate.keys.forEach { dateItem ->
                val transactions = state.transactionsByDate[dateItem].orEmpty()
                item { TransactionGroupDateItem(groupDateItem = dateItem) }

                items(transactions) { transaction ->
                    TransactionItem(transactionItem = transaction)
                }
            }
        }
    }
}

@Composable
private fun AddNewTransactionFab(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@PhonePreviews
@Composable
private fun PreviewTransactionsScreen() {
    MyApplicationTheme {
        val dateItem1 = GroupDateItem(day = "TUE", date = "8 August 2023", dayTotal = "€641.00")
        val dateItem2 = GroupDateItem(day = "MON", date = "7 August 2023", dayTotal = "€641.00")
        val dateItem3 = GroupDateItem(day = "SUN", date = "6 August 2023", dayTotal = "€641.00")
        val dateItem4 = GroupDateItem(day = "SAT", date = "5 August 2023", dayTotal = "€641.00")
        val dateItem5 = GroupDateItem(day = "FRI", date = "4 August 2023", dayTotal = "€641.00")

        val transaction1 = TransactionItem(
            type = TransactionType.INCOME,
            name = "Sales Commission",
            category = "Bonus",
            amount = "€753.00",
            icon = Icons.Filled.AirplanemodeActive,
        )
        val transaction2 = TransactionItem(
            type = TransactionType.EXPENSE,
            name = "Coffee Shop",
            category = "Dining Out",
            amount = "-€23.00",
            icon = Icons.Outlined.Dining,
        )
        val transaction3 = TransactionItem(
            type = TransactionType.EXPENSE,
            name = "Cable TV",
            category = "Cable",
            amount = "-€89.00",
            icon = Icons.Outlined.Tv,
        )
        val dateTransactions = listOf(transaction1, transaction2, transaction3)

        val transactions: LinkedHashMap<GroupDateItem, List<TransactionItem>> = LinkedHashMap()
        transactions[dateItem1] = dateTransactions
        transactions[dateItem2] = dateTransactions
        transactions[dateItem3] = dateTransactions
        transactions[dateItem4] = dateTransactions
        transactions[dateItem5] = dateTransactions

        val state = TransactionListUiState(transactionsByDate = transactions)
        TransactionsScreen(
            state = state,
            onAddTransaction = { },
        )
    }
}
