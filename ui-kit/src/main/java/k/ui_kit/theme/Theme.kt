package k.ui_kit.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    primary = orangePrimary,
    onPrimary = blackBackgroundDark,
    secondary = greenSecondary,
    onSecondary = blackBackgroundDark,
    error = redError,
    onError = blackBackgroundDark,
    background = blackBackgroundDark,
    onBackground = whiteTextDark,
    surface = grayOnBackgroundDark,
    onSurface = whiteTextDark,
)

val LightColorScheme = lightColorScheme(
    primary = orangePrimary,
    onPrimary = whiteBackgroundLight,
    secondary = greenSecondary,
    onSecondary = whiteBackgroundLight,
    error = redError,
    onError = whiteBackgroundLight,
    background = whiteBackgroundLight,
    onBackground = blackTextLight,
    surface = grayOnBackgroundLight,
    onSurface = blackTextLight,
)

@Composable
fun NotesshiftlabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, //true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
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
        shapes = Shapes,
        content = content
    )
}