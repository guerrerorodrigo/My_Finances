package com.rodrigoguerrero.myfinances.android.ui.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodrigoguerrero.myfinances.android.ui.display.screens.TransactionsScreen
import com.rodrigoguerrero.myfinances.android.ui.main.navigation.MainDestinations.Home
import com.rodrigoguerrero.myfinances.android.ui.main.navigation.MainDestinations.Transactions

@Composable
fun MainGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(navController = navController, startDestination = Home.route) {
        composable(route = Home.route) {
            Text(text = "home")
        }
        composable(route = Transactions.route) {
            TransactionsScreen(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            )
        }
    }
}
