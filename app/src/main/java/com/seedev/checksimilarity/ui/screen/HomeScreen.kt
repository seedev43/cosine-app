package com.seedev.checksimilarity.ui.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seedev.checksimilarity.ui.components.CustomButton
import com.seedev.checksimilarity.ui.navigation.Routes
import com.seedev.checksimilarity.ui.theme.BrutalRed
import com.seedev.checksimilarity.ui.theme.BrutalYellow

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
                text = "Riwayat Perhitungan",
                onClick = {
                    navController.navigate(Routes.HISTORY)
                },
                backgroundColor = BrutalYellow
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = "Tentang Aplikasi",
                onClick = {
                    navController.navigate(Routes.ABOUT)
                },
                backgroundColor = BrutalRed
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}