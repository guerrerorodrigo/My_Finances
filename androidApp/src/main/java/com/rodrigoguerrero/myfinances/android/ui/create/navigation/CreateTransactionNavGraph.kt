package com.rodrigoguerrero.myfinances.android.ui.create.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.components.SelectCategoryBottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.components.SelectIconBottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.models.AddCategoryUiState
import com.rodrigoguerrero.myfinances.android.ui.categories.models.SelectCategoryUiState
import com.rodrigoguerrero.myfinances.android.ui.categories.models.categoryIcons
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionEvent
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionUiState
import com.rodrigoguerrero.myfinances.android.ui.create.screens.AddCategoryGroupScreen
import com.rodrigoguerrero.myfinances.android.ui.create.screens.AddNewCategoryScreen
import com.rodrigoguerrero.myfinances.android.ui.create.screens.AddTransactionScreen

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.createTransactionNavGraph(navController: NavHostController) {
    navigation(
        route = "create-transaction",
        startDestination = "add-transaction"
    ) {
        composable(route = "add-transaction") {
            AddTransactionScreen(
                state = AddTransactionUiState(),
                onEvent = { event ->
                    when (event) {
                        AddTransactionEvent.NavigateBack -> {
                            navController.navigate("main") {
                                popUpTo("add-transaction") {
                                    inclusive = true
                                }
                            }
                        }

                        AddTransactionEvent.ShowCategoryPicker -> navController.navigate("category-picker")
                        else -> {}
                    }
                }
            )
        }
        bottomSheet(route = "category-picker") {
            SelectCategoryBottomSheet(
                selectCategoryUiState = SelectCategoryUiState(),
                onAddNewCategory = { navController.navigate("add-category") },
                onSelected = {},
            )
        }
        composable(route = "add-category") {
            AddNewCategoryScreen(
                state = AddCategoryUiState(),
                onAddNewCategoryGroup = { navController.navigate("add-new-category-group") },
                onCategorySelected = {},
                onChangeIcon = { navController.navigate("change-icon") },
                onBack = { navController.popBackStack() },
                onSave = { }
            )
        }
        composable(route = "add-new-category-group") {
            AddCategoryGroupScreen(
                onBack = { navController.popBackStack() },
                onComplete = { navController.popBackStack() },
            )
        }
        bottomSheet(route = "change-icon") {
            SelectIconBottomSheet(
                icons = categoryIcons,
                onIconSelected = {},
            )
        }
    }
}
