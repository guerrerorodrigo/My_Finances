package com.rodrigoguerrero.myfinances.data.local.categories.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionType
import database.CategoryGroupEntity

data class CategoryGroupDto(
    val id: Long,
    val name: String,
    val type: TransactionType,
)

internal fun CategoryGroupEntity.toDto() = CategoryGroupDto(
    id = id,
    name = name,
    type = TransactionType.from(type),
)
