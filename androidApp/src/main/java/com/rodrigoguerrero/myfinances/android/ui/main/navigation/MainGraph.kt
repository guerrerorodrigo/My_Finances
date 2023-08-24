package com.rodrigoguerrero.myfinances.android.ui.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            Text(text = "home")
        }
        composable(route = "transactions") {
            Text(text = "transactions")
        }
    }
}
