package com.rodrigoguerrero.myfinances.android.ui.categories.navigation

import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinationsParams.IsExpense

sealed class CreateCategoryDestinations(val route: String) {
    data class CategoryCreation(val isExpense: Boolean? = null) : CreateCategoryDestinations(
        route = if (isExpense != null) {
            "category-creation?isExpense=$isExpense"
        } else {
            "category-creation?isExpense={$IsExpense}"
        }
    )

    data object AddCategory : CreateCategoryDestinations("add-category")
    data object AddCategoryGroup : CreateCategoryDestinations("add-new-category-group")
    data object ChangeIcon : CreateCategoryDestinations("change-icon")
}

sealed class CreateCategoryDestinationsParams(val name: String) {
    data object IsExpense : CreateCategoryDestinationsParams("isExpense")
}