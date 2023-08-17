package com.rodrigoguerrero.myfinances.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

object AppTheme {
    val color: ColorScheme
        @Composable
        get() = LocalAppColors.current

    val padding: AppPadding
        @Composable
        get() = LocalAppPadding.current

    val typography: Typography
        @Composable
        get() = LocalAppTypography.current

    val shapes: Shapes
        @Composable
        get() = LocalAppShapes.current
}

@Composable
fun MyApplicationTheme(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    val padding = AppPadding()
    val typography = typography()

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppPadding provides padding,
        LocalAppTypography provides typography,
        LocalAppShapes provides Shapes,
    ) {
        MaterialTheme(
            colorScheme = colors,
            shapes = Shapes,
            typography = typography,
        ) {
            Surface(
                modifier = modifier.fillMaxSize(),
            ) {
                content()
            }
        }
    }
}
