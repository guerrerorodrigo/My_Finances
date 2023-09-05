package com.rodrigoguerrero.myfinances.android.ui.categories.navigation

import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinationsParams.TransactionTypeParam
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType

sealed class CreateCategoryDestinations(val route: String) {
    data class CategoryCreation(val transactionType: TransactionType? = null) : CreateCategoryDestinations(
        route = if (transactionType != null) {
            "category-creation?transactionType=${transactionType.ordinal}"
        } else {
            "category-creation?transactionType={${TransactionTypeParam.name}}"
        }
    )

    data object AddCategory : CreateCategoryDestinations("add-category")
    data object AddCategoryGroup : CreateCategoryDestinations("add-new-category-group")
    data object ChangeIcon : CreateCategoryDestinations("change-icon")
}

sealed class CreateCategoryDestinationsParams(val name: String) {
    data object TransactionTypeParam : CreateCategoryDestinationsParams("transactionType")
}
