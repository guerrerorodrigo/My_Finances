package com.rodrigoguerrero.myfinances.data.local.categories.datasource

import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryDto
import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryGroupDto
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import kotlinx.coroutines.flow.Flow

interface CategoriesDataSource {

    suspend fun getAllCategories(): Flow<List<CategoryDto>>
    fun getAllCategoryGroups(
        transactionType: TransactionType,
    ): Flow<List<CategoryGroupDto>>
    suspend fun insertCategoryGroup(name: String, transactionType: TransactionType)
}
