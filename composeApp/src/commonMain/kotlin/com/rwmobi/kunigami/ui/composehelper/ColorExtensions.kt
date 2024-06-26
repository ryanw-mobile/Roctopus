/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.ui.composehelper

import androidx.compose.ui.graphics.Color

internal fun Color.darken(factor: Float): Color {
    return Color(
        red = (this.red * factor).coerceIn(0f, 1f),
        green = (this.green * factor).coerceIn(0f, 1f),
        blue = (this.blue * factor).coerceIn(0f, 1f),
        alpha = this.alpha,
    )
}

internal fun Color.luminance(): Float {
    return 0.299f * red + 0.587f * green + 0.114f * blue
}

internal fun Color.getContrastColor(
    colorDark: Color = Color.Black,
    colorLight: Color = Color.White,
): Color {
    val luminance = luminance()
    return if (luminance > 0.5) colorDark else colorLight
}
