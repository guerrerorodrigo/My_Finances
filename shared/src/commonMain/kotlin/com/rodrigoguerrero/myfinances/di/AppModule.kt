package com.rodrigoguerrero.myfinances.di

import com.rodrigoguerrero.myfinances.data.di.dataModule
import com.rodrigoguerrero.myfinances.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        dataModule,
        domainModule,
    )
}
