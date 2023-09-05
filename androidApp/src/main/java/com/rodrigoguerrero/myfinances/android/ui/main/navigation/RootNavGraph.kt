package com.rodrigoguerrero.myfinances.android.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodrigoguerrero.myfinances.android.ui.create.navigation.CreateTransactionDestinations.CreateTransaction
import com.rodrigoguerrero.myfinances.android.ui.create.navigation.createTransactionNavGraph
import com.rodrigoguerrero.myfinances.android.ui.main.navigation.MainDestinations.Main
import com.rodrigoguerrero.myfinances.android.ui.main.screens.MainScreen

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = Main.route) {
        composable(route = Main.route) {
            MainScreen(
                onAddTransaction = { navHostController.navigate(CreateTransaction.route) }
            )
        }
        createTransactionNavGraph(navController = navHostController)
    }
}
