package com.rodrigoguerrero.myfinances.data.local.models

data class CategoryDto(
    val id: Long,
    val name: String,
    val icon: String?,
    val groupId: Int,
)
