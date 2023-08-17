package com.rodrigoguerrero.myfinances.data.di

import com.rodrigoguerrero.myfinances.data.local.datasource.TransactionsDataSource
import com.rodrigoguerrero.myfinances.data.local.datasource.SqlDelightTransactionsDataSource
import com.rodrigoguerrero.myfinances.database.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single<TransactionsDataSource> { SqlDelightTransactionsDataSource(database = get()) }
    single { AppDatabase(driver = get()) }
}
