package com.rodrigoguerrero.myfinances.android.ui.create.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepository
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionEvent
import com.rodrigoguerrero.myfinances.ui.transactioins.viewmodels.AddTransactionViewModel

class AndroidAddTransactionViewModel(
    private val transactionRepository: TransactionRepository,
) : ViewModel() {

    private val viewModel by lazy {
        AddTransactionViewModel(
            coroutineScope = viewModelScope,
            repository = transactionRepository,
        )
    }

    val state = viewModel.state

    fun onEvent(event: AddTransactionEvent) {
        viewModel.onEvent(event)
    }
}
