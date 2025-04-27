package com.seedev.sicekam.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.neoBrutalism(
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Black,
    shadowColor: Color = Color.Black,
    cornerRadius: Dp = 16.dp,
    borderWidth: Dp = 5.dp,
    shadowOffsetX: Dp = 6.dp,
    shadowOffsetY: Dp = 6.dp
): Modifier = this.then(
    Modifier
        .offset(x = shadowOffsetX, y = shadowOffsetY)
        .background(shadowColor, RoundedCornerShape(cornerRadius))
).then(
    Modifier
        .background(backgroundColor, RoundedCornerShape(cornerRadius))
        .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius))
)
