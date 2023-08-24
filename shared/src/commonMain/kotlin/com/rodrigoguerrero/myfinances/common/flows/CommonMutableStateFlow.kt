package com.rodrigoguerrero.myfinances.common.flows

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>) : MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow(): MutableStateFlow<T> =
    CommonMutableStateFlow(this)