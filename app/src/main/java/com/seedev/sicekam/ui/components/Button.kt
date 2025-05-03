package com.seedev.sicekam.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seedev.sicekam.ui.theme.BrutalBrown
import com.seedev.sicekam.ui.theme.CustomShadowColor

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    widthPercentage: Float = 0.8f, // default lebar 80%
    height: Dp = 56.dp,
    fontSize: TextUnit = 18.sp,
    shape: Dp = 14.dp,
    stroke: Dp = 4.dp,
    strokeColor: Color = BrutalBrown,
    shadowColor: Color = CustomShadowColor,
    backgroundColor: Color = Color.White,
    contentColor: Color = BrutalBrown
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Manual shadow layer
        Box(
            modifier = Modifier
                .fillMaxWidth(widthPercentage)
                .height(height) // biar ukuran sama dengan button
                .offset(x = 6.dp, y = 6.dp) // arah shadow: kanan bawah
                .background(
                    color = shadowColor, // shadow pekat
                    shape = RoundedCornerShape(shape)
                )
        )

        // Button di atas shadow
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(shape),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = contentColor
            ),
            border = BorderStroke(stroke, strokeColor),
            modifier = Modifier
                .fillMaxWidth(widthPercentage)
                .height(height)
        ) {
            Text(
                text = text,
                fontSize = fontSize
            )
        }
    }
}