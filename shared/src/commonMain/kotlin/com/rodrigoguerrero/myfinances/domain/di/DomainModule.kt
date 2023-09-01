package com.rodrigoguerrero.myfinances.domain.di

import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepository
import com.rodrigoguerrero.myfinances.domain.categories.repositories.CategoryRepositoryImpl
import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepository
import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    single<TransactionRepository> { TransactionRepositoryImpl(dataSource = get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(dataSource = get()) }
}
