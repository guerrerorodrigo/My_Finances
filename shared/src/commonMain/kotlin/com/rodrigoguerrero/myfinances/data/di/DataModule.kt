package com.rodrigoguerrero.myfinances.data.di

import com.rodrigoguerrero.myfinances.data.local.categories.datasource.CategoriesDataSource
import com.rodrigoguerrero.myfinances.data.local.categories.datasource.SqlDelightCategoriesDataSource
import com.rodrigoguerrero.myfinances.data.local.transactions.datasource.TransactionsDataSource
import com.rodrigoguerrero.myfinances.data.local.transactions.datasource.SqlDelightTransactionsDataSource
import com.rodrigoguerrero.myfinances.database.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single<TransactionsDataSource> { SqlDelightTransactionsDataSource(database = get()) }
    single { AppDatabase(driver = get()) }
    single<CategoriesDataSource> { SqlDelightCategoriesDataSource(appDatabase = get()) }
}
