package com.seedev.checksimilarity.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seedev.checksimilarity.ui.theme.BackgroundColor
import com.seedev.checksimilarity.ui.theme.BrutalBrown
import com.seedev.checksimilarity.ui.theme.CustomShadowColor

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    height: Dp = 120.dp,
    strokeColor: Color = BrutalBrown,
    shadowColor: Color = CustomShadowColor,
    backgroundColor: Color = BackgroundColor,
    shape: Dp = 14.dp,
    borderStroke: Dp = 4.dp,
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .offset(x = 6.dp, y = 6.dp) // arah shadow: kanan bawah
                .background(
                    color = shadowColor, // shadow pekat
                    shape = RoundedCornerShape(shape)
                )
        )

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(backgroundColor, shape = RoundedCornerShape(shape))
                .border(BorderStroke(borderStroke, strokeColor), shape = RoundedCornerShape(shape))
                .padding(12.dp),
            textStyle = TextStyle(
                fontSize = 14.sp
            ),
            value = text,
            onValueChange = onValueChange,
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = TextStyle(color = Color.Gray)
                    )
                }
                innerTextField()
            }

        )
    }
}
