package com.rodrigoguerrero.myfinances.android.ui.display.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepository
import com.rodrigoguerrero.myfinances.ui.transactioins.viewmodels.TransactionsViewModel

class AndroidTransactionsViewModel(
    private val transactionRepository: TransactionRepository,
) : ViewModel() {
    private val viewModel by lazy {
        TransactionsViewModel(
            coroutineScope = viewModelScope,
            repository = transactionRepository,
        )
    }

    val state = viewModel.state
}
