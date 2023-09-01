package com.rodrigoguerrero.myfinances.android

import android.app.Application
import com.rodrigoguerrero.myfinances.android.ui.di.uiModule
import com.rodrigoguerrero.myfinances.data.di.androidDataModule
import com.rodrigoguerrero.myfinances.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class FinancesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@FinancesApp)
            modules(androidDataModule, uiModule)
        }
    }
}
