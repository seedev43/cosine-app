package com.seedev.checksimilarity.ui.screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seedev.checksimilarity.api.RetrofitClient
import com.seedev.checksimilarity.model.SimilarityResult
import com.seedev.checksimilarity.ui.components.CustomButton
import com.seedev.checksimilarity.ui.components.CustomTextField
import com.seedev.checksimilarity.ui.theme.BackgroundColor
import com.seedev.checksimilarity.ui.theme.BrutalGreen
import com.seedev.checksimilarity.ui.theme.BrutalBrown
import com.seedev.checksimilarity.ui.theme.BrutalRed
import com.seedev.checksimilarity.ui.theme.CustomShadowColor
import com.seedev.checksimilarity.utils.SharedPrefHelper
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun CheckSimilarityScreen(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var temp by remember {
        mutableStateOf(
            SimilarityResult(
                originalText1 = "",
                originalText2 = "",
                processedText1 = "",
                processedText2 = "",
                similarity = 0.0,
                similarityPercent = "",
                similarityStatus = "",
            )
        )
    }

    var isLoading by remember { mutableStateOf(false) }
    var buttonActive by remember { mutableStateOf(false) }

    fun submitAction() {
        if (text1.isNotBlank() && text2.isNotBlank()) {
            isLoading = true
            result = ""
            temp = SimilarityResult(
                originalText1 = "",
                originalText2 = "",
                processedText1 = "",
                processedText2 = "",
                similarity = 0.0,
                similarityPercent = "",
                similarityStatus = "",
            )

            coroutineScope.launch {
                try {
                    val response = RetrofitClient.service.checkSimilarity(text1, text2)
                    temp = SimilarityResult(
                        originalText1 = text1,
                        originalText2 = text2,
                        processedText1 = response.result.processedText1,
                        processedText2 = response.result.processedText2,
                        similarity = response.result.similarity,
                        similarityPercent = response.result.similarityPercent,
                        similarityStatus = response.result.similarityStatus,
                        timestamp = System.currentTimeMillis()
                    )
                    result = "Skor Kemiripan: ${response.result.similarityPercent}\nStatus Kemiripan: ${response.result.similarityStatus}"
                } catch (e: Exception) {
                    e.printStackTrace()
                    result = "Terjadi kesalahan! Mohon coba beberapa saat lagi."
                } finally {
                    isLoading = false
                }
            }
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
                title = { Text("Cek Kemiripan", color = BrutalBrown) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = BrutalBrown
                        )
                    }
                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.popBackStack()
//                    }) {
//                        Icon(
//                            modifier = Modifier.size(30.dp),
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back",
//                            tint = BrutalBrown
//                        )
//                    }
//                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
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

                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .matchParentSize()
//                        .height(200.dp)
                            .offset(x = 6.dp, y = 6.dp)
                            .background(
                                color = CustomShadowColor,
                                shape = RoundedCornerShape(14.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
//                        .wrapContentHeight()
//                        .height(200.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(BackgroundColor)
                            .border(
                                BorderStroke(4.dp, BrutalBrown),
                                shape = RoundedCornerShape(14.dp)
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

                                CustomButton(
                                    text = "Simpan",
                                    onClick = {
                                        SharedPrefHelper.saveToHistory(context, temp)
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Berhasil disimpan ke riwayat")
                                        }
                                        result = ""
                                    },
                                    fontSize = 14.sp,
                                    widthPercentage = 0.4f,
                                    height = 40.dp,
                                    backgroundColor = BrutalGreen
                                )

                                CustomButton(text = "Jangan Simpan",
                                    onClick = { result = "" },
                                    fontSize = 14.sp,
                                    height = 40.dp,
                                    backgroundColor = BrutalRed
                                )
                            }
                        }

                    }
                }
            }
                    Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun preview() {
    CheckSimilarityScreen(rememberNavController())
}