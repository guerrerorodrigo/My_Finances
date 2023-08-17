package com.rodrigoguerrero.myfinances.data.di

import com.rodrigoguerrero.myfinances.database.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

val iosDataModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(AppDatabase.Schema, "finances.db")
    }
}
