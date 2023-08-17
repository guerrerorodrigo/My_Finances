package com.rodrigoguerrero.myfinances.data.di

import com.rodrigoguerrero.myfinances.database.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDataModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = androidContext(),
            name = "finances.db",
        )
    }
}
