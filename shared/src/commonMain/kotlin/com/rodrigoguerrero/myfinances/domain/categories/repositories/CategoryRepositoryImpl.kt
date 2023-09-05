package com.rodrigoguerrero.myfinances.domain.categories.repositories

import com.rodrigoguerrero.myfinances.data.local.categories.datasource.CategoriesDataSource
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.models.CategoryGroup
import com.rodrigoguerrero.myfinances.domain.categories.models.toCategoryGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CategoryRepositoryImpl(
    private val dataSource: CategoriesDataSource,
) : CategoryRepository {

    override suspend fun addCategoryGroup(name: String, transactionType: TransactionType) {
        dataSource.insertCategoryGroup(name, transactionType)
    }

    override fun getCategoryGroups(
        transactionType: TransactionType,
    ): Flow<List<CategoryGroup>> =
        dataSource
            .getAllCategoryGroups(transactionType)
            .map { groups -> groups.map { group -> group.toCategoryGroup() } }
}
