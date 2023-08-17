package com.rodrigoguerrero.myfinances.di

import com.rodrigoguerrero.myfinances.data.di.iosDataModule

fun initKoin() {
    initKoin {
        modules(iosDataModule)
    }
}
