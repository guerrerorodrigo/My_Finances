package com.rodrigoguerrero.myfinances.domain.categories.models

import com.rodrigoguerrero.myfinances.data.local.transactions.models.TransactionDto

data class Category(
    val id: Long,
    val name: String,
    val group: CategoryGroup,
)

internal fun TransactionDto.toCategory() = Category(
    id = categoryId,
    name = category,
    group = CategoryGroup(id = categoryGroupId, name = categoryGroup),
)
