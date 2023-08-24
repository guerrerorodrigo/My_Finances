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
            errorLabelColor = AppTheme.color.onSurfaceVariant,
            disabledLabelColor = AppTheme.color.onSurfaceVariant,
            disabledTrailingIconColor = AppTheme.color.onSurfaceVariant,
            errorTrailingIconColor = AppTheme.color.onSurfaceVariant,
            focusedTrailingIconColor = AppTheme.color.onSurfaceVariant,
            unfocusedTrailingIconColor = AppTheme.color.onSurfaceVariant,
            disabledTextColor = AppTheme.color.onSurfaceVariant,
            errorTextColor = AppTheme.color.onSurfaceVariant,
            focusedTextColor = AppTheme.color.onSurfaceVariant,
            unfocusedTextColor = AppTheme.color.onSurfaceVariant,
            unfocusedIndicatorColor = AppTheme.color.onSurfaceVariant,
            focusedIndicatorColor = AppTheme.color.onSurfaceVariant,
            errorIndicatorColor = AppTheme.color.onSurfaceVariant,
            disabledIndicatorColor = AppTheme.color.onSurfaceVariant,
        ),
        readOnly = isReadOnly,
        enabled = isEnabled,
        trailingIcon = trailingIcon,
    )
}
