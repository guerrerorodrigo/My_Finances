package com.rodrigoguerrero.myfinances.android.ui.main.navigation

sealed class MainDestinations(val route: String) {
    data object Main : MainDestinations("main")
    data object Home : MainDestinations("home")
    data object Transactions : MainDestinations("transactions")
}
