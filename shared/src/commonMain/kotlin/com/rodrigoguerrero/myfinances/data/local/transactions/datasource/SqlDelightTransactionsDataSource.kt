package com.rodrigoguerrero.myfinances.data.local.transactions.datasource

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionDto
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.data.local.transactions.models.toTransactionDto
import com.rodrigoguerrero.myfinances.database.AppDatabase
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.TransactionEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

internal class SqlDelightTransactionsDataSource(database: AppDatabase) : TransactionsDataSource {

    private val queries = database.financesQueries

    override suspend fun getAllTransactions(): Flow<List<TransactionDto>> =
        queries.getAllTransactions().toDto()

    override suspend fun getTransactionsByType(
        transactionType: TransactionType,
    ): Flow<List<TransactionDto>> =
        queries.getTransactionByType(transactionType.ordinal.toLong()).toDto()

    override suspend fun getTransactionsById(id: Long): Flow<List<TransactionDto>> =
        queries.getTransactionById(id).toDto()

    override suspend fun insertTransaction(transaction: TransactionDto) {
        queries.insertTransactionEntity(
            id = transaction.id,
            name = transaction.name,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            amount = transaction.amount,
            type = transaction.type.ordinal.toLong(),
            category = transaction.category,
            categoryGroupId = transaction.categoryGroupId,
            categoryId = transaction.categoryId,
            notes = transaction.notes,
            categoryGroup = transaction.categoryGroup,
        )
    }

    override suspend fun deleteTransaction(id: Long) {
        queries.deleteTransaction(id)
    }

    private fun Query<TransactionEntity>.toDto() = this.asFlow()
        .mapToList()
        .map { entities ->
            supervisorScope {
                entities
                    .map { entity -> async { entity.toTransactionDto() } }
                    .map { it.await() }
            }
        }
}