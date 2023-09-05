package com.rodrigoguerrero.myfinances.data.local.categories.datasource

import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryDto
import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryGroupDto
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import kotlinx.coroutines.flow.Flow

interface CategoriesDataSource {

    fun getAllCategories(): Flow<List<CategoryDto>>
    fun getAllCategoryGroups(
        transactionType: TransactionType,
    ): Flow<List<CategoryGroupDto>>
    suspend fun addCategoryGroup(name: String, transactionType: TransactionType)
    suspend fun addCategory(name: String, groupId: Int, groupName: String, iconPosition: Int?)
}
