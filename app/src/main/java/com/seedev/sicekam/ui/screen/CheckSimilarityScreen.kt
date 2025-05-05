package com.seedev.sicekam.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.seedev.sicekam.api.RetrofitClient
import com.seedev.sicekam.ui.components.CustomButton
import com.seedev.sicekam.ui.components.CustomTextField
import com.seedev.sicekam.ui.theme.BackgroundColor
import com.seedev.sicekam.ui.theme.BrutalGreen
import com.seedev.sicekam.ui.theme.BrutalBrown
import com.seedev.sicekam.ui.theme.CustomShadowColor
import com.seedev.sicekam.utils.neoBrutalism
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun CheckSimilarityScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    fun submitAction() {
        if (text1.isNotBlank() && text2.isNotBlank()) {
            isLoading = true
            coroutineScope.launch {
                try {
                    val response = RetrofitClient.service.checkSimilarity(
                        text1, text2
//                                    CheckSimilarityRequest(text1, text2)
                    )
                    result = "Skor Kemiripan: ${response.result.similarityPercent}\nStatus Kemiripan: ${response.result.similarityStatus}"
                } catch (e: Exception) {
                    Log.e("ERROR NICH", e.toString())
                    e.printStackTrace()
                    result = "Terjadi kesalahan! Mohon coba beberapa saat lagi."
                } finally {
                    isLoading = false
                }
            }
        } else {
            result = "Kalimat tidak boleh kosong!"
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BrutalGreen,
                    titleContentColor = BrutalBrown
                ),
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = BrutalBrown
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
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "Cek Kemiripan Kalimat",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = BrutalBrown
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Kalimat 1", fontWeight = FontWeight.Medium)
            CustomTextField(
                text = text1,
                onValueChange = { text1 = it },
                placeholder = "Masukkan kalimat 1"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Kalimat 2", fontWeight = FontWeight.Medium)
            CustomTextField(
                text = text2,
                onValueChange = { text2 = it },
                placeholder = "Masukkan kalimat 2"
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButton(
                text = if (isLoading) "Loading..." else "Submit",
                onClick = { submitAction() },
                widthPercentage = 1f,
                backgroundColor = BrutalGreen
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (result.isNotBlank()) {
                Spacer(modifier = Modifier.height(20.dp))

                // Card untuk menampilkan hasil kemiripan
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(
                            BorderStroke(2.dp, BrutalBrown),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Hasil Kemiripan",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = BrutalBrown
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = result,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = {
                                    // Simpan ke riwayat (misalnya ke local db)
                                    Log.d("RIWAYAT", "Disimpan ke riwayat: $result")
                                    // TODO: Tambahkan penyimpanan ke local storage/Room
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Simpan")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    result = ""
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Jangan Simpan")
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun preview() {
    CheckSimilarityScreen(rememberNavController())
}