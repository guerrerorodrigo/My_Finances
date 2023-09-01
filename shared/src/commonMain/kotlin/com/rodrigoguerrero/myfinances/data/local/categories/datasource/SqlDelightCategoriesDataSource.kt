package com.rodrigoguerrero.myfinances.data.local.categories.datasource

import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryDto
import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryGroupDto
import com.rodrigoguerrero.myfinances.data.local.categories.models.toCategoryDto
import com.rodrigoguerrero.myfinances.data.local.categories.models.toDto
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.database.AppDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope

internal class SqlDelightCategoriesDataSource(appDatabase: AppDatabase) : CategoriesDataSource {

    private val queries = appDatabase.categoriesQueries

    override suspend fun getAllCategories(): Flow<List<CategoryDto>> =
        queries
            .getAllCategories()
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities
                        .map { entity -> async { entity.toCategoryDto() } }
                        .map { it.await() }
                }
            }

    override suspend fun getAllCategoryGroups(
        transactionType: TransactionType,
    ): Flow<List<CategoryGroupDto>> =
        queries
            .getCategoryGroupsByType(transactionType.ordinal.toLong())
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities
                        .map { entity -> async { entity.toDto() } }
                        .map { it.await() }
                }
            }

    override suspend fun insertCategoryGroup(name: String, transactionType: TransactionType) {
        queries.insertCategoryGroupEntity(
            id = 0,
            name = name,
            type = transactionType.ordinal.toLong(),
        )
    }
}
