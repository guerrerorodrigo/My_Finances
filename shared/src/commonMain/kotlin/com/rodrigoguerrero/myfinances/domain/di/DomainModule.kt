package com.rodrigoguerrero.myfinances.domain.di

import com.rodrigoguerrero.myfinances.domain.repository.TransactionRepository
import com.rodrigoguerrero.myfinances.domain.repository.TransactionRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    single<TransactionRepository> { TransactionRepositoryImpl(dataSource = get()) }
}
