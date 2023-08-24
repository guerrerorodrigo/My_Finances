package com.rodrigoguerrero.myfinances.common.flows

import kotlinx.coroutines.flow.StateFlow

expect class CommonStateFlow<T>(flow: StateFlow<T>): StateFlow<T>

fun <T> StateFlow<T>.toCommonStateFlow(): CommonStateFlow<T> = CommonStateFlow(this)
