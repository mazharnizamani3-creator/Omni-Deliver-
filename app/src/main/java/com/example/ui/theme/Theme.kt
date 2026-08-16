package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = SecondaryMint,
    onPrimary = Color.White,
    primaryContainer = PrimaryForest,
    onPrimaryContainer = MintLight,
    secondary = SecondaryMint,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF1E3A2F),
    onSecondaryContainer = MintLight,
    tertiary = AmberGold,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF452C06),
    onTertiaryContainer = AmberLight,
    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkSurfaceCard,
    onSurfaceVariant = DarkTextSecondary,
    outline = Color(0xFF475569),
    outlineVariant = Color(0xFF334155),
  )

private val LightColorScheme =
  lightColorScheme(
    primary = PrimaryGreen,
    onPrimary = Color.White,
    primaryContainer = MintLight,
    onPrimaryContainer = PrimaryForest,
    secondary = SecondaryMint,
    onSecondary = Color.White,
    secondaryContainer = MintBackground,
    onSecondaryContainer = PrimaryGreenDark,
    tertiary = AmberGold,
    onTertiary = Color.White,
    tertiaryContainer = AmberLight,
    onTertiaryContainer = Color(0xFF78350F),
    background = CanvasBackground,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCardAlt,
    onSurfaceVariant = TextSecondary,
    outline = BorderSubtle,
    outlineVariant = BorderDivider,
  )

@Composable
fun OmniDeliverTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Keep consistent green and white branding by default
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
