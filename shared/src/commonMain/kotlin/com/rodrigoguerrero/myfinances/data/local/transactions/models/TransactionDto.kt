package com.rodrigoguerrero.myfinances.data.local.transactions.models

import database.TransactionEntity

data class TransactionDto(
    val id: Long,
    val name: String,
    val type: TransactionType,
    val amount: Double,
    val date: String,
    val time: String,
    val notes: String,
    val category: String,
    val categoryId: Long,
    val categoryGroupId: Long,
    val categoryGroup: String,
)

internal fun TransactionEntity.toTransactionDto() = TransactionDto(
    id = id,
    name = name,
    type = TransactionType.from(type),
    amount = amount,
    date = date,
    time = time,
    category = category,
    categoryId = categoryId,
    categoryGroupId = categoryGroupId,
    notes = notes.orEmpty(),
    categoryGroup = categoryGroup,
)
