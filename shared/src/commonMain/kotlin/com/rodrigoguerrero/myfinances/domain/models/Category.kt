package com.rodrigoguerrero.myfinances.domain.models

import com.rodrigoguerrero.myfinances.data.local.models.TransactionDto

data class Category(
    val id: Long,
    val name: String,
    val group: CategoryGroup,
)

internal fun TransactionDto.toCategory() = Category(
    id = categoryId,
    name = category,
    group = toCategoryGroup(),
)
