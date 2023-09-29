package com.rodrigoguerrero.myfinances.ui.transactioins.viewmodels

import com.rodrigoguerrero.myfinances.common.flows.CommonStateFlow
import com.rodrigoguerrero.myfinances.common.flows.toCommonStateFlow
import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepository
import com.rodrigoguerrero.myfinances.ui.transactioins.models.GroupDateItem
import com.rodrigoguerrero.myfinances.ui.transactioins.models.TransactionItem
import com.rodrigoguerrero.myfinances.ui.transactioins.models.TransactionListUiState
import com.rodrigoguerrero.myfinances.utils.formatDateWithDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class TransactionsViewModel(
    repository: TransactionRepository,
    coroutineScope: CoroutineScope?,
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(TransactionListUiState())
    val state: CommonStateFlow<TransactionListUiState> =
        combine(
            _state,
            repository.getAllTransactions(),
        ) { state, transactions ->
            val transactionsByDate = transactions
                .map {
                    Pair(
                        first = formatDateWithDay(
                            Instant.fromEpochMilliseconds(it.date)
                                .toLocalDateTime(TimeZone.currentSystemDefault())
                        ),
                        second = it
                    )
                }
                .sortedByDescending { it.first }
                .groupBy { it.first }
                .mapValues {
                    it.value.map { transaction ->
                        TransactionItem(
                            amount = transaction.second.amount.toString(),
                            category = transaction.second.category.name,
                            name = transaction.second.name,
                            type = transaction.second.type,
                            iconPosition = transaction.second.category.iconPosition,
                        )
                    }
                }
                .mapKeys {
                    GroupDateItem(
                        date = it.key,
                        dayTotal = it.value
                            .sumOf { transaction -> transaction.amount.toDouble() }.toString(),
                    )
                }
            state.copy(transactionsByDate = transactionsByDate)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TransactionListUiState(),
        ).toCommonStateFlow()
}
