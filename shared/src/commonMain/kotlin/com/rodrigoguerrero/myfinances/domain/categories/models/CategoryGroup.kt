package com.rodrigoguerrero.myfinances.domain.categories.models

import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryGroupDto

data class CategoryGroup(
    val id: Long,
    val name: String,
)

internal fun CategoryGroupDto.toCategoryGroup() = CategoryGroup(
    id = id,
    name = name,
)
