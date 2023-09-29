package com.rodrigoguerrero.myfinances.data.local.transactions.datasource

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionDto
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import kotlinx.coroutines.flow.Flow

interface TransactionsDataSource {
    fun getAllTransactions(): Flow<List<TransactionDto>>
    suspend fun insertTransaction(transaction: TransactionDto)
    suspend fun getTransactionsByType(transactionType: TransactionType): Flow<List<TransactionDto>>
    suspend fun getTransactionsById(id: Long): Flow<List<TransactionDto>>
    suspend fun deleteTransaction(id: Long)
}
