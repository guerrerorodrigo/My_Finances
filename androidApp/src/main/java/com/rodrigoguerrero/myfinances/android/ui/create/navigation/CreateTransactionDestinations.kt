package com.rodrigoguerrero.myfinances.android.ui.create.navigation

sealed class CreateTransactionDestinations(val route: String) {
    data object CreateTransaction : CreateTransactionDestinations("create-transaction")
    data object AddTransaction : CreateTransactionDestinations("add-transaction")
    data object CategoryPicker : CreateTransactionDestinations("category-picker")
}
