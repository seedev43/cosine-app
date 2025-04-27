package com.seedev.sicekam.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seedev.sicekam.ui.components.CustomButton
import com.seedev.sicekam.ui.components.CustomTextField
import com.seedev.sicekam.ui.theme.BackgroundColor
import com.seedev.sicekam.ui.theme.CustomColor1
import com.seedev.sicekam.ui.theme.CustomShadowColor
import com.seedev.sicekam.utils.neoBrutalism

@ExperimentalMaterial3Api
@Composable
fun CheckSimilarityScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Cek Kemiripan", fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = CustomColor1
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(15.dp)
        ) {
            var text by remember { mutableStateOf("") }
            Text(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                text = "Kalimat 1",
                fontWeight = FontWeight.Medium
            )
            CustomTextField(
                text = text,
                onValueChange = {},
                placeholder = "Masukkan kalimat 1"
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                text = "Kalimat 2",
                fontWeight = FontWeight.Medium
            )
            CustomTextField(
                text = text,
                onValueChange = {},
                placeholder = "Masukkan kalimat 2"
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButton(
                text = "Submit",
                onClick = {},
                widthPercentage = 1f,
                backgroundColor = Color(0xFFb5ead6)
            )
        }
    }
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun preview() {
    CheckSimilarityScreen(rememberNavController())
}