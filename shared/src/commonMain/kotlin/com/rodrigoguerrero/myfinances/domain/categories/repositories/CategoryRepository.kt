package com.rodrigoguerrero.myfinances.domain.categories.repositories

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.models.CategoryGroup
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun addCategoryGroup(name: String, transactionType: TransactionType)
    fun getCategoryGroups(
        transactionType: TransactionType,
    ): Flow<List<CategoryGroup>>
}
