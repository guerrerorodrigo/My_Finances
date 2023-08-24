package com.rodrigoguerrero.myfinances.android.ui.main.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.myfinances.android.ui.main.navigation.MainGraph

@Composable
fun MainScreen(
    onAddTransaction: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = null)
                    }
                    IconButton(
                        onClick = { navController.navigate("transactions") },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SyncAlt,
                            contentDescription = null,
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = onAddTransaction) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->
        MainGraph(navController = navController, paddingValues = padding)
    }
}
