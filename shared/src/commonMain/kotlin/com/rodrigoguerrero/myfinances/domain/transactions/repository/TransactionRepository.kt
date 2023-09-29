package com.rodrigoguerrero.myfinances.domain.transactions.repository

import com.rodrigoguerrero.myfinances.domain.transactions.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun createTransaction(transaction: Transaction)
    fun getAllTransactions(): Flow<List<Transaction>>
}