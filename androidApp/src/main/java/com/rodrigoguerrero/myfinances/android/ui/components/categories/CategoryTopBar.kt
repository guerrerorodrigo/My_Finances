package com.rodrigoguerrero.myfinances.android.ui.components.categories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.myfinances.android.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CategoryTopBar(
    title: String,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title, style = AppTheme.typography.titleMedium) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = null)
            }
        }
    )
}
