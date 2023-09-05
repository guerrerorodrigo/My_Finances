package com.rodrigoguerrero.myfinances.android.ui.create.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import com.rodrigoguerrero.myfinances.android.ui.common.components.TimePickerDialog
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionEvent

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TransactionTimePickerDialog(
    onEvent: (AddTransactionEvent) -> Unit,
    hour: Int,
    minutes: Int,
) {
    val timePickerState = rememberTimePickerState(
        is24Hour = true,
        initialHour = hour,
        initialMinute = minutes,
    )

    TimePickerDialog(
        onCancel = { onEvent(AddTransactionEvent.HideTimePicker) },
        onConfirm = {
            onEvent(
                AddTransactionEvent.OnTimeSelected(
                    hour = timePickerState.hour,
                    minute = timePickerState.minute,
                )
            )
            onEvent(AddTransactionEvent.HideTimePicker)
        },
        onDismissRequest = { onEvent(AddTransactionEvent.HideTimePicker) }
    ) {
        TimePicker(state = timePickerState)
    }
}
