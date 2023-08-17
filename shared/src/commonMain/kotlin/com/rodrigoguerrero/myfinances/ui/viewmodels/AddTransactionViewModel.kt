package com.rodrigoguerrero.myfinances.ui.viewmodels

import com.rodrigoguerrero.myfinances.domain.repository.TransactionRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AddTransactionViewModel : ViewModel(), KoinComponent {

    private val repository: TransactionRepository = get()

}