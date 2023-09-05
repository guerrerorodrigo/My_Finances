package com.rodrigoguerrero.myfinances.data.local.categories.models

import database.CategoryEntity

data class CategoryDto(
    val id: Long,
    val name: String,
    val icon: Long?,
    val groupId: Long,
    val groupName: String,
)

internal fun CategoryEntity.toCategoryDto() = CategoryDto(
    id = id,
    name = name,
    icon = icon,
    groupId = groupId,
    groupName = groupName,
)
