package com.rodrigoguerrero.myfinances.domain.categories.models

import com.rodrigoguerrero.myfinances.data.local.categories.models.CategoryDto

data class Category(
    val id: Long,
    val name: String,
    val iconPosition: Int?,
    val group: CategoryGroup,
)

internal fun CategoryDto.toCategory() = Category(
    id = id,
    name = name,
    iconPosition = icon?.toInt(),
    group = CategoryGroup(id = groupId, name = groupName),
)
