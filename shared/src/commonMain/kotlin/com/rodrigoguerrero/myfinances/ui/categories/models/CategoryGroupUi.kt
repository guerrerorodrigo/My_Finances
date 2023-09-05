package com.rodrigoguerrero.myfinances.ui.categories.models

import com.rodrigoguerrero.myfinances.domain.categories.models.CategoryGroup

data class CategoryGroupUi(
    val id: Int,
    val name: String,
)

internal fun CategoryGroup.toUi() = CategoryGroupUi(
    id = id.toInt(),
    name = name,
)
