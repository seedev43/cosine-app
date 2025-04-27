package com.seedev.sicekam.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seedev.sicekam.ui.components.CustomButton
import com.seedev.sicekam.ui.navigation.Routes

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Menu Utama",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )


            Text(
                text = "Aplikasi Cek Kemiripan Kalimat",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(15.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            CustomButton(
                text = "Cek Kemiripan",
                onClick = {
                    navController.navigate(Routes.SIMILARITY)
                },
                backgroundColor = Color(0xFFb5ead6)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = "Riwayat Penggunaan",
                onClick = {
                    navController.navigate(Routes.HISTORY)
                },
                backgroundColor = Color(0xFFF5E24D)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = "Tentang Aplikasi",
                onClick = {
                    navController.navigate(Routes.ABOUT)
                },
                backgroundColor = Color(0xFFff9ba3)
            )

        }
    }
}