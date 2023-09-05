package com.rodrigoguerrero.myfinances.android.ui.create.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.components.SelectCategoryBottomSheet
import com.rodrigoguerrero.myfinances.android.ui.categories.models.SelectCategoryUiState
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.CreateCategoryDestinations.CategoryCreation
import com.rodrigoguerrero.myfinances.android.ui.categories.navigation.createCategoryNavGraph
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionEvent.NavigateBack
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionEvent.ShowCategoryPicker
import com.rodrigoguerrero.myfinances.android.ui.create.models.AddTransactionUiState
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
        composable(route = AddTransaction.route) {
            AddTransactionScreen(
                state = AddTransactionUiState(),
                onEvent = { event ->
                    when (event) {
                        NavigateBack -> {
                            navController.navigate(Main.route) {
                                popUpTo(AddTransaction.route) {
                                    inclusive = true
                                }
                            }
                        }

                        ShowCategoryPicker -> navController.navigate(CategoryPicker.route)
                        else -> {}
                    }
                }
            )
        }
        bottomSheet(route = CategoryPicker.route) {
            SelectCategoryBottomSheet(
                selectCategoryUiState = SelectCategoryUiState(),
                onAddNewCategory = { navController.navigate(CategoryCreation(it).route) },
                onSelected = {},
            )
        }
        createCategoryNavGraph(navController)
    }
}
