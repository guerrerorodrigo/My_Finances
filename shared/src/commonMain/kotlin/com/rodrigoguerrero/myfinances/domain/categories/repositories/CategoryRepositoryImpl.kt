package com.rodrigoguerrero.myfinances.domain.categories.repositories

import com.rodrigoguerrero.myfinances.data.local.categories.datasource.CategoriesDataSource
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.models.Category
import com.rodrigoguerrero.myfinances.domain.categories.models.CategoryGroup
import com.rodrigoguerrero.myfinances.domain.categories.models.toCategory
import com.rodrigoguerrero.myfinances.domain.categories.models.toCategoryGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

internal class CategoryRepositoryImpl(
    private val dataSource: CategoriesDataSource,
) : CategoryRepository {

    override suspend fun addCategoryGroup(name: String, transactionType: TransactionType) {
        dataSource.addCategoryGroup(name, transactionType)
    }

    override fun getCategoryGroups(
        transactionType: TransactionType,
    ): Flow<List<CategoryGroup>> =
        dataSource
            .getAllCategoryGroups(transactionType)
            .map { groups -> groups.map { group -> group.toCategoryGroup() } }

    override suspend fun addCategory(
        name: String,
        groupId: Int,
        groupName: String,
        iconPosition: Int?
    ) {
        dataSource.addCategory(name, groupId, groupName, iconPosition)
    }

    override fun getCategories(transactionType: TransactionType): Flow<List<Category>> =
        combine(
            dataSource.getAllCategories(),
            dataSource.getAllCategoryGroups(transactionType)
        ) { categories, groups ->
            val groupIds = groups.map { group -> group.id }
            val categoriesForTransactionType = categories.filter { it.groupId in groupIds }
            categoriesForTransactionType.map { it.toCategory() }
        }
}
