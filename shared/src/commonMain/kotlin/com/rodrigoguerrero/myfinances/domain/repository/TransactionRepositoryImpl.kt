package com.rodrigoguerrero.myfinances.domain.repository

import com.rodrigoguerrero.myfinances.data.local.datasource.TransactionsDataSource
import com.rodrigoguerrero.myfinances.domain.models.Transaction
import com.rodrigoguerrero.myfinances.domain.models.toDomain
import com.rodrigoguerrero.myfinances.domain.models.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TransactionRepositoryImpl(
    private val dataSource: TransactionsDataSource
) : TransactionRepository {

    override suspend fun createTransaction(transaction: Transaction) {
        dataSource.insertTransaction(transaction.toDto())
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> = dataSource
        .getAllTransactions().map { transactions ->
            transactions.map { transaction -> transaction.toDomain() }
        }
}
