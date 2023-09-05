package com.rodrigoguerrero.myfinances.android.ui.create.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.components.SelectCategoryBottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.models.SelectCategoryUiState
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.createCategoryNavGraph
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionEvent.NavigateBack
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionEvent.ShowCategoryPicker
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionUiState
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
                        NavigateBack -> {
                            navController.navigate("main") {
                                popUpTo("add-transaction") {
                                    inclusive = true
                                }
                            }
                        }

                        ShowCategoryPicker -> navController.navigate("category-picker")
                        else -> {}
                    }
                }
            )
        }
        bottomSheet(route = "category-picker") {
            SelectCategoryBottomSheet(
                selectCategoryUiState = SelectCategoryUiState(),
                onAddNewCategory = { navController.navigate("category-creation?isExpense=$it") },
                onSelected = {},
            )
        }
        createCategoryNavGraph(navController)
    }
}
