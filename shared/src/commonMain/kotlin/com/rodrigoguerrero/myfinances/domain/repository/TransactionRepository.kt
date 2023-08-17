package com.rodrigoguerrero.myfinances.domain.repository

import com.rodrigoguerrero.myfinances.data.local.models.TransactionDto
import com.rodrigoguerrero.myfinances.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun createTransaction(transaction: Transaction)
    suspend fun getAllTransactions(): Flow<List<Transaction>>
}