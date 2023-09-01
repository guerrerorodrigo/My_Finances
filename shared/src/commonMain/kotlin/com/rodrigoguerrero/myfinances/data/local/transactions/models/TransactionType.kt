package com.rodrigoguerrero.myfinances.data.local.transactions.models

enum class TransactionType {
    INCOME, EXPENSE, UNKNOWN;

    companion object {
        infix fun from(value: Long): TransactionType =
            TransactionType.values().firstOrNull { it.ordinal == value.toInt() } ?: UNKNOWN
    }
}
