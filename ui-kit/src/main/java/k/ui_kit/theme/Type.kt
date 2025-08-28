package k.ui_kit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import k.ui_kit.R

val Andika = FontFamily(
    Font(R.font.andika)
)

val Noto = FontFamily(
    Font(R.font.noto_sans_mono)
)
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Noto,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Noto,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Noto,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Noto,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Noto,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Noto,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
)