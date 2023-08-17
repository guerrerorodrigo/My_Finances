package com.rodrigoguerrero.myfinances.data.local.models

import database.TransactionEntity

data class TransactionDto(
    val id: Long,
    val name: String,
    val type: TransactionType,
    val amount: Double,
    val creationDate: Long,
)

internal fun TransactionEntity.toTransactionDto() = TransactionDto(
    id = id,
    name = name,
    type = TransactionType.from(type),
    amount = amount,
    creationDate = createdAt,
)
