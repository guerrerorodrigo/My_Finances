package com.rodrigoguerrero.myfinances.android.ui.components.add

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.rodrigoguerrero.myfinances.android.ui.models.add.AddTransactionEvent

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddTransactionScreenTopAppBar(onEvent: (AddTransactionEvent) -> Unit) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { onEvent(AddTransactionEvent.NavigateBack) }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
            }
        }
    )
}
