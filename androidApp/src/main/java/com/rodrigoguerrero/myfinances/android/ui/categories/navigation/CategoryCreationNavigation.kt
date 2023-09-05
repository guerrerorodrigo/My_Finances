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
import com.rodrigoguerrero.myfinances.android.ui.categories.screens.AddCategoryGroupScreen
import com.rodrigoguerrero.myfinances.android.ui.categories.screens.AddNewCategoryScreen

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.createCategoryNavGraph(navController: NavHostController) {
    navigation(
        route = "category-creation?isExpense={isExpense}",
        startDestination = "add-category",
    ) {
        composable(route = "add-category") { navBackStack ->
            val backStackEntry = remember(navController.currentBackStackEntry) { navController.getBackStackEntry("add-category") }
            val isExpense = remember(navBackStack) {
                navController
                    .getBackStackEntry("category-creation?isExpense={isExpense}")
                    .arguments
                    ?.getString("isExpense") == "true"
            }
            AddNewCategoryScreen(
                onAddNewCategoryGroup = { navController.navigate("add-new-category-group") },
                onChangeIcon = { navController.navigate("change-icon") },
                onBack = {
                    navController.navigate("main") {
                        popUpTo("category-creation?isExpense=$isExpense") {
                            inclusive = true
                        }
                    }
                },
                onSave = { },
                isExpense = isExpense,
                viewModelStoreOwner = backStackEntry,
            )
        }
        composable(route = "add-new-category-group") {
            AddCategoryGroupScreen(
                onBack = { navController.popBackStack() },
                onComplete = { navController.popBackStack() },
            )
        }
        bottomSheet(route = "change-icon") {
            val backStackEntry = remember(navController.currentBackStackEntry) { navController.getBackStackEntry("add-category") }
            SelectIconBottomSheet(
                icons = categoryIcons,
                onIconSelected = { navController.popBackStack() },
                viewModelStoreOwner = backStackEntry,
            )
        }
    }
}
