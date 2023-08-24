package com.rodrigoguerrero.myfinances.domain.models

import com.rodrigoguerrero.myfinances.data.local.models.TransactionDto

data class CategoryGroup(
    val id: Long,
    val name: String,
)

internal fun TransactionDto.toCategoryGroup() = CategoryGroup(
    id = categoryGroupId,
    name = categoryGroup,
)
