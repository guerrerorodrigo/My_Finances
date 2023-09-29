package com.rodrigoguerrero.myfinances.android.ui.display.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rodrigoguerrero.myfinances.android.ui.common.annotations.PhonePreviews
import com.rodrigoguerrero.myfinances.android.ui.display.components.TransactionGroupDateItem
import com.rodrigoguerrero.myfinances.android.ui.display.components.TransactionItem
import com.rodrigoguerrero.myfinances.android.ui.display.viewmodels.AndroidTransactionsViewModel
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme
import com.rodrigoguerrero.myfinances.android.ui.theme.MyApplicationTheme
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.transactioins.models.GroupDateItem
import com.rodrigoguerrero.myfinances.ui.transactioins.models.TransactionItem
import com.rodrigoguerrero.myfinances.ui.transactioins.models.TransactionListUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    viewModel: AndroidTransactionsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TransactionsScreen(
        state = state,
        modifier = modifier,
    )
}

@Composable
private fun TransactionsScreen(
    state: TransactionListUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.m),
        modifier = modifier,
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

@PhonePreviews
@Composable
private fun PreviewTransactionsScreen() {
    MyApplicationTheme {
        val dateItem1 = GroupDateItem(date = "8 August 2023", dayTotal = "€641.00")
        val dateItem2 = GroupDateItem(date = "7 August 2023", dayTotal = "€641.00")
        val dateItem3 = GroupDateItem(date = "6 August 2023", dayTotal = "€641.00")
        val dateItem4 = GroupDateItem(date = "5 August 2023", dayTotal = "€641.00")
        val dateItem5 = GroupDateItem(date = "4 August 2023", dayTotal = "€641.00")

        val transaction1 = TransactionItem(
            type = TransactionType.INCOME,
            name = "Sales Commission",
            category = "Bonus",
            amount = "€753.00",
            iconPosition = 0,
        )
        val transaction2 = TransactionItem(
            type = TransactionType.EXPENSE,
            name = "Coffee Shop",
            category = "Dining Out",
            amount = "-€23.00",
            iconPosition = 1,
        )
        val transaction3 = TransactionItem(
            type = TransactionType.EXPENSE,
            name = "Cable TV",
            category = "Cable",
            amount = "-€89.00",
            iconPosition = 2,
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
        )
    }
}
