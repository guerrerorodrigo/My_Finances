package com.rodrigoguerrero.myfinances.domain.models

import com.rodrigoguerrero.myfinances.data.local.models.TransactionDto
import com.rodrigoguerrero.myfinances.data.local.models.TransactionType

data class Transaction(
    val id: Long,
    val name: String,
    val type: TransactionType,
    val amount: Double,
    val creationDate: Long,
    val notes: String,
    val category: Category,
)

internal fun TransactionDto.toDomain() = Transaction(
    id = id,
    name = name,
    type = type,
    amount = amount,
    creationDate = creationDate,
    category = toCategory(),
    notes = notes,
)

internal fun Transaction.toDto() = TransactionDto(
    id = id,
    name = name,
    type = type,
    amount = amount,
    creationDate = creationDate,
    notes = notes,
    categoryId = category.id,
    category = category.name,
    categoryGroupId = category.group.id,
    categoryGroup = category.group.name,
)
