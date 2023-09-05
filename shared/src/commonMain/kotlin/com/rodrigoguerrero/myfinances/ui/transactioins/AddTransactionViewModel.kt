package com.rodrigoguerrero.myfinances.ui.transactioins

import com.rodrigoguerrero.myfinances.domain.transactions.repository.TransactionRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AddTransactionViewModel : ViewModel(), KoinComponent {

    private val repository: TransactionRepository = get()

}