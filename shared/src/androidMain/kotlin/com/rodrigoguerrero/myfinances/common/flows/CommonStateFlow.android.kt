package com.rodrigoguerrero.myfinances.common.flows

import kotlinx.coroutines.flow.StateFlow

actual class CommonStateFlow<T> actual constructor(
    private val flow: StateFlow<T>
) : StateFlow<T> by flow

