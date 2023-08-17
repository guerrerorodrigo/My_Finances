package com.rodrigoguerrero.myfinances.android.ui.common.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme

@Composable
fun TransactionTextField(
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    prefix: String? = null,
    isReadOnly: Boolean = false,
    isEnabled: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = text,
        keyboardOptions = keyboardOptions,
        readOnly = isReadOnly,
        enabled = isEnabled,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(text = placeholder) },
        label = label,
        maxLines = maxLines,
        minLines = 1,
        leadingIcon = if (leadingIcon != null) {
            { Icon(imageVector = leadingIcon, contentDescription = null) }
        } else {
            null
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = AppTheme.color.primary,
            disabledLabelColor = AppTheme.color.onSurfaceVariant,
            errorLabelColor = AppTheme.color.onSurfaceVariant,
            focusedLabelColor = AppTheme.color.onSurfaceVariant,
            unfocusedLabelColor = AppTheme.color.onSurfaceVariant,
            disabledTextColor = AppTheme.color.onSurface,
            disabledLeadingIconColor = AppTheme.color.onSurface,
        ),
        prefix = if (prefix != null) {
            { Text(text = prefix) }
        } else {
            null
        }
    )
}
