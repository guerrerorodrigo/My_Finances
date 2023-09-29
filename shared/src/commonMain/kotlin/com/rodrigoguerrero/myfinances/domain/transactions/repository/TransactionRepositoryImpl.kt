package com.rodrigoguerrero.myfinances.domain.transactions.repository

import com.rodrigoguerrero.myfinances.data.local.transactions.datasource.TransactionsDataSource
import com.rodrigoguerrero.myfinances.domain.transactions.models.Transaction
import com.rodrigoguerrero.myfinances.domain.transactions.models.toDomain
import com.rodrigoguerrero.myfinances.domain.transactions.models.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TransactionRepositoryImpl(
    private val dataSource: TransactionsDataSource
) : TransactionRepository {

    override suspend fun createTransaction(transaction: Transaction) {
        dataSource.insertTransaction(transaction.toDto())
    }

    override fun getAllTransactions(): Flow<List<Transaction>> = dataSource
        .getAllTransactions().map { transactions ->
            transactions.map { transaction -> transaction.toDomain() }
        }
}
