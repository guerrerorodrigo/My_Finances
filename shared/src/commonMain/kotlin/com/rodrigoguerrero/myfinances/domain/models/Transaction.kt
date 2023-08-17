package com.rodrigoguerrero.myfinances.domain.models

import com.rodrigoguerrero.myfinances.data.local.models.TransactionDto
import com.rodrigoguerrero.myfinances.data.local.models.TransactionType

data class Transaction(
    val id: Long,
    val name: String,
    val type: TransactionType,
    val amount: Double,
    val creationDate: Long,
)

internal fun TransactionDto.toDomain() = Transaction(
    id = id,
    name = name,
    type = type,
    amount = amount,
    creationDate = creationDate,
)

internal fun Transaction.toDto() = TransactionDto(
    id = id,
    name = name,
    type = type,
    amount = amount,
    creationDate = creationDate,
)
