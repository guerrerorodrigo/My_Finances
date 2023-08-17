package com.rodrigoguerrero.myfinances.android.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightBackground
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightError
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightErrorContainer
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightInverseOnSurface
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightInversePrimary
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnBackground
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnError
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnPrimary
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnPrimaryContainer
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnSecondary
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnSecondaryContainer
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnSurface
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnSurfaceVariant
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnTertiary
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOnTertiaryContainer
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOutline
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightOutlineVariant
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightPrimary
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightPrimaryContainer
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightScrim
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightSecondary
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightSecondaryContainer
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightSurface
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightSurfaceTint
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightSurfaceVariant
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightTertiary
import com.rodrigoguerrero.myfinances.ui.theme.Colors.lightTertiaryContainer

val LightColors = lightColorScheme(
    primary = Color(lightPrimary),
    onPrimary = Color(lightOnPrimary),
    primaryContainer = Color(lightPrimaryContainer),
    onPrimaryContainer = Color(lightOnPrimaryContainer),
    secondary = Color(lightSecondary),
    onSecondary = Color(lightOnSecondary),
    secondaryContainer = Color(lightSecondaryContainer),
    onSecondaryContainer = Color(lightOnSecondaryContainer),
    tertiary = Color(lightTertiary),
    onTertiary = Color(lightOnTertiary),
    tertiaryContainer = Color(lightTertiaryContainer),
    onTertiaryContainer = Color(lightOnTertiaryContainer),
    error = Color(lightError),
    errorContainer = Color(lightErrorContainer),
    onError = Color(lightOnError),
    onErrorContainer = Color(lightErrorContainer),
    background = Color(lightBackground),
    onBackground = Color(lightOnBackground),
    surface = Color(lightSurface),
    onSurface = Color(lightOnSurface),
    surfaceVariant = Color(lightSurfaceVariant),
    onSurfaceVariant = Color(lightOnSurfaceVariant),
    outline = Color(lightOutline),
    inverseOnSurface = Color(lightInverseOnSurface),
    inverseSurface = Color(lightInverseOnSurface),
    inversePrimary = Color(lightInversePrimary),
    surfaceTint = Color(lightSurfaceTint),
    outlineVariant = Color(lightOutlineVariant),
    scrim = Color(lightScrim),
)

val LocalAppColors = staticCompositionLocalOf { LightColors }
