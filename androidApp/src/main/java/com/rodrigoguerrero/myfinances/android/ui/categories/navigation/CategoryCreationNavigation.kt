package com.rodrigoguerrero.myfinances.android.ui.categories.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.components.SelectIconBottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.models.categoryIcons
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinations.AddCategory
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinations.AddCategoryGroup
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinations.CategoryCreation
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinations.ChangeIcon
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinationsParams.IsExpense
import com.rodrigoguerrero.myfinances.android.ui.categories.screens.AddCategoryGroupScreen
import com.rodrigoguerrero.myfinances.android.ui.categories.screens.AddNewCategoryScreen
import com.rodrigoguerrero.myfinances.android.ui.create.navigation.CreateTransactionDestinations.CreateTransaction

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.createCategoryNavGraph(navController: NavHostController) {
    navigation(
        route = CategoryCreation().route,
        startDestination = AddCategory.route,
    ) {
        composable(route = AddCategory.route) { navBackStack ->
            val backStackEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(AddCategory.route)
            }
            val isExpense = remember(navBackStack) {
                navController
                    .getBackStackEntry(CategoryCreation().route)
                    .arguments
                    ?.getString(IsExpense.name) == "true"
            }
            AddNewCategoryScreen(
                onAddNewCategoryGroup = { navController.navigate(AddCategoryGroup.route) },
                onChangeIcon = { navController.navigate(ChangeIcon.route) },
                onBack = {
                    navController.navigate(CreateTransaction.route) {
                        popUpTo(CategoryCreation(isExpense).route) {
                            inclusive = true
                        }
                    }
                },
                isExpense = isExpense,
                viewModelStoreOwner = backStackEntry,
            )
        }
        composable(route = AddCategoryGroup.route) {
            AddCategoryGroupScreen(
                onBack = { navController.popBackStack() },
                onComplete = { navController.popBackStack() },
            )
        }
        bottomSheet(route = ChangeIcon.route) {
            val backStackEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(AddCategory.route)
            }
            SelectIconBottomSheet(
                icons = categoryIcons,
                onIconSelected = { navController.popBackStack() },
                viewModelStoreOwner = backStackEntry,
            )
        }
    }
}
