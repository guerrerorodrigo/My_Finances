package com.rodrigoguerrero.myfinances.android.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodrigoguerrero.myfinances.android.ui.create.navigation.createTransactionNavGraph
import com.rodrigoguerrero.myfinances.android.ui.main.screens.MainScreen

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = "main") {
        composable(route = "main") {
            MainScreen(
                onAddTransaction = { navHostController.navigate("create-transaction") }
            )
        }
        createTransactionNavGraph(navController = navHostController)
    }
}
