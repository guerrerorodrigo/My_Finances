package com.rodrigoguerrero.myfinances.android.ui.categories.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme

@Composable
fun AddCategoryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isReadOnly: Boolean = false,
    isError: Boolean = false,
    error: String = "",
    isEnabled: Boolean = true,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(text = label) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedLabelColor = AppTheme.color.onSurfaceVariant,
            focusedLabelColor = AppTheme.color.onSurfaceVariant,
            disabledLabelColor = AppTheme.color.onSurfaceVariant,
            disabledTrailingIconColor = AppTheme.color.onSurfaceVariant,
            errorTrailingIconColor = AppTheme.color.onSurfaceVariant,
            focusedTrailingIconColor = AppTheme.color.onSurfaceVariant,
            unfocusedTrailingIconColor = AppTheme.color.onSurfaceVariant,
            disabledTextColor = AppTheme.color.onSurfaceVariant,
            focusedTextColor = AppTheme.color.onSurfaceVariant,
            unfocusedTextColor = AppTheme.color.onSurfaceVariant,
            unfocusedIndicatorColor = AppTheme.color.onSurfaceVariant,
            focusedIndicatorColor = AppTheme.color.onSurfaceVariant,
            disabledIndicatorColor = AppTheme.color.onSurfaceVariant,
            disabledSupportingTextColor = AppTheme.color.error,
        ),
        readOnly = isReadOnly,
        enabled = isEnabled,
        trailingIcon = trailingIcon,
        isError = isError,
        supportingText = { Text(text = error) },
    )
}
