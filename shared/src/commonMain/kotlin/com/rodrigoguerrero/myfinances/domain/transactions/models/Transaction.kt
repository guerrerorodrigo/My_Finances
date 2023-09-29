package com.rodrigoguerrero.myfinances.domain.transactions.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionDto
import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import com.rodrigoguerrero.myfinances.domain.categories.models.Category
import com.rodrigoguerrero.myfinances.domain.categories.models.CategoryGroup

data class Transaction(
    val id: Long,
    val name: String,
    val type: TransactionType,
    val amount: Double,
    val date: Long,
    val time: String,
    val notes: String,
    val category: Category,
)

internal fun TransactionDto.toDomain() = Transaction(
    id = id,
    name = name,
    type = type,
    amount = amount,
    date = date,
    time = time,
    category = Category(
        id = categoryId,
        name = category,
        iconPosition = categoryIconPosition,
        group = CategoryGroup(id = categoryGroupId, name = categoryGroup),
    ),
    notes = notes,
)

internal fun Transaction.toDto() = TransactionDto(
    id = id,
    name = name,
    type = type,
    amount = amount,
    date = date,
    time = time,
    notes = notes,
    categoryId = category.id,
    category = category.name,
    categoryGroupId = category.group.id,
    categoryGroup = category.group.name,
    categoryIconPosition = category.iconPosition,
)
