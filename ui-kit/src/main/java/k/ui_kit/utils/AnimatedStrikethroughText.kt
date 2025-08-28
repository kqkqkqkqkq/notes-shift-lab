package k.ui_kit.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AnimatedStrikethroughText(
    text: String,
    isVisible: Boolean,
    color: Color,
    style: TextStyle,
    modifier: Modifier = Modifier,
    strikethroughStyle: SpanStyle = SpanStyle(
        color = Color.Black.copy(alpha = 0.32f),
        textDecoration = TextDecoration.LineThrough
    ),
    animateOnHide: Boolean = true,
    spec: AnimationSpec<Int> = tween(durationMillis = 400)
) {
    val isPreview = LocalInspectionMode.current

    val textToDisplay = if (isPreview) {
        if (isVisible) text.buildStrikethrough(text.length, strikethroughStyle)
        else AnnotatedString(text)
    } else {
        var stateText by remember { mutableStateOf(AnnotatedString(text)) }
        val length = remember { Animatable(0, Int.VectorConverter) }

        LaunchedEffect(length.value) {
            stateText = text.buildStrikethrough(length.value, strikethroughStyle)
        }

        LaunchedEffect(isVisible) {
            when {
                isVisible -> length.animateTo(text.length, spec)
                !isVisible && animateOnHide -> length.animateTo(0, spec)
                else -> length.snapTo(0)
            }
        }

        LaunchedEffect(text) {
            if (isVisible && text.length == length.value) {
                stateText = text.buildStrikethrough(length.value, strikethroughStyle)
            } else if (isVisible && text.length != length.value) {
                length.snapTo(text.length)
            } else {
                stateText = AnnotatedString(text)
            }
        }

        stateText
    }

    Text(
        text = textToDisplay,
        modifier = modifier,
        style = style,
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
