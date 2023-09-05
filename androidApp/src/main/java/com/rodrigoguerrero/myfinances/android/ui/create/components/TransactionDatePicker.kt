package com.rodrigoguerrero.myfinances.android.ui.create.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.myfinances.android.R
import com.rodrigoguerrero.myfinances.ui.transactioins.models.AddTransactionEvent

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TransactionDatePicker(
    onEvent: (AddTransactionEvent) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = { onEvent(AddTransactionEvent.HideDatePicker) },
        confirmButton = {
            TextButton(
                content = { Text(text = stringResource(id = R.string.select)) },
                onClick = {
                    onEvent(AddTransactionEvent.OnDateSelected(date = datePickerState.selectedDateMillis))
                    onEvent(AddTransactionEvent.HideDatePicker)
                }
            )
        },
        dismissButton = {
            TextButton(
                content = { Text(text = stringResource(id = R.string.cancel)) },
                onClick = { onEvent(AddTransactionEvent.HideDatePicker) }
            )
        },
    ) {
        DatePicker(state = datePickerState)
    }
}
