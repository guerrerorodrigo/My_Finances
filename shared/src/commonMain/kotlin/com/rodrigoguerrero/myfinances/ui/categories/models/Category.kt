package com.rodrigoguerrero.myfinances.ui.categories.models

import com.rodrigoguerrero.myfinances.domain.categories.models.CategoryGroup
import com.rodrigoguerrero.myfinances.domain.categories.models.Category as DomainCategory

data class Category(
    val id: Int,
    val name: String,
    val iconPosition: Int?,
    val group: String,
    val groupId: Long,
)

fun Category.toDomain() = DomainCategory(
    id = id.toLong(),
    name = name,
    iconPosition = iconPosition,
    group = CategoryGroup(id = groupId, name = group),
)
