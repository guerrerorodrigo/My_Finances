package com.rodrigoguerrero.myfinances.android.ui.create.viewmodels

import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.myfinances.android.ui.create.models.TransactionCreationUiState
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.ui.categories.models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TransactionCreationViewModel : ViewModel() {

    private val _state = MutableStateFlow(TransactionCreationUiState())
    val state: StateFlow<TransactionCreationUiState> = _state

    fun onCategorySelected(category: Category) {
        _state.update { it.copy(selectedCategory = category) }
    }

    fun setTransactionType(transactionType: TransactionType) {
        _state.update { it.copy(transactionType = transactionType) }
    }
}
