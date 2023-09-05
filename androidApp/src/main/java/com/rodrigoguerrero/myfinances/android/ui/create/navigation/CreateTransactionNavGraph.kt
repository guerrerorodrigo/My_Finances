package com.rodrigoguerrero.myfinances.android.ui.create.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.components.SelectCategoryBottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinations.CategoryCreation
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.createCategoryNavGraph
import com.rodrigoguerrero.myfinances.android.ui.create.navigation.CreateTransactionDestinations.AddTransaction
import com.rodrigoguerrero.myfinances.android.ui.create.navigation.CreateTransactionDestinations.CategoryPicker
import com.rodrigoguerrero.myfinances.android.ui.create.navigation.CreateTransactionDestinations.CreateTransaction
import com.rodrigoguerrero.myfinances.android.ui.create.screens.AddTransactionScreen
import com.rodrigoguerrero.myfinances.android.ui.main.navigation.MainDestinations.Main

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.createTransactionNavGraph(navController: NavHostController) {
    navigation(
        route = CreateTransaction.route,
        startDestination = AddTransaction.route,
    ) {
        composable(route = AddTransaction.route) { navStack ->
            val backStackEntry = remember(navStack) {
                navController.getBackStackEntry(AddTransaction.route)
            }
            AddTransactionScreen(
                onBack = {
                    navController.navigate(Main.route) {
                        popUpTo(CreateTransaction.route) {
                            inclusive = true
                        }
                    }
                },
                onShowCategoryPicker = {
                    navController.navigate(CategoryPicker.route)
                },
                viewModelStoreOwner = backStackEntry,
            )
        }
        bottomSheet(route = CategoryPicker.route) {
            val backStackEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(AddTransaction.route)
            }
            SelectCategoryBottomSheet(
                onAddNewCategory = { navController.navigate(CategoryCreation(it).route) },
                viewModelStoreOwner = backStackEntry,
                onSelected = { navController.popBackStack() }
            )
        }
        createCategoryNavGraph(navController)
    }
}
