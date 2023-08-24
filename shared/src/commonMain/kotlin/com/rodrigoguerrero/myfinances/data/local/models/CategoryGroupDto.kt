package com.rodrigoguerrero.myfinances.data.local.models

data class CategoryGroupDto(
    val id: Long,
    val name: String,
    val type: TransactionType,
)
