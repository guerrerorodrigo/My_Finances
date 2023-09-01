package com.rodrigoguerrero.myfinances.data.local.categories.models

import database.CategoryEntity

data class CategoryDto(
    val id: Long,
    val name: String,
    val icon: String?,
    val groupId: Long,
)

internal fun CategoryEntity.toCategoryDto() = CategoryDto(
    id = id,
    name = name,
    icon = icon,
    groupId = groupId,
)
