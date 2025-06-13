package com.seedev.checksimilarity.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seedev.checksimilarity.ui.components.CustomButton
import com.seedev.checksimilarity.ui.theme.BackgroundColor
import com.seedev.checksimilarity.ui.theme.BrutalBrown
import com.seedev.checksimilarity.ui.theme.BrutalGreen
import com.seedev.checksimilarity.ui.theme.BrutalRed
import com.seedev.checksimilarity.ui.theme.CustomShadowColor
import com.seedev.checksimilarity.utils.SharedPrefHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@ExperimentalMaterial3Api
@Composable
fun HistoryScreen(navController: NavController) {
    val context = LocalContext.current
    var historyList by remember { mutableStateOf(SharedPrefHelper.getHistory(context).toMutableList()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat Perhitungan", color = BrutalBrown) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BrutalGreen),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = BrutalBrown
                        )
                    }
                },
                actions = {
                    if (historyList.isNotEmpty()) {
                        TextButton(onClick = {
                            SharedPrefHelper.clearHistory(context)
                            historyList = mutableListOf()

                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Semua riwayat dihapus")
                            }
                        }) {
                            Text("Hapus Semua", color = BrutalBrown)
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (historyList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Belum ada riwayat", fontSize = 16.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(historyList) { item ->
                    Box(
                        modifier = Modifier.padding(bottom = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .matchParentSize()
                                .offset(x = 6.dp, y = 6.dp) // arah shadow: kanan bawah
                                .background(
                                    color = CustomShadowColor, // shadow pekat
                                    shape = RoundedCornerShape(14.dp)
                                )
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
//                                .padding(bottom = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = BackgroundColor, contentColor = BrutalBrown),
                            border = BorderStroke(3.dp, BrutalBrown)
//                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Kalimat 1: ${item.originalText1}", fontSize = 14.sp)
                                Text("Kalimat 2: ${item.originalText2}", fontSize = 14.sp)
                                Text("Kemiripan: ${item.similarityPercent}", fontSize = 14.sp)
                                Text("Status: ${item.similarityStatus}", fontSize = 14.sp)
                                Text(
                                    text = "Waktu: ${SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID")).apply {
                                        timeZone = TimeZone.getTimeZone("Asia/Jakarta")
                                    }.format(Date(item.timestamp))}",
                                    fontSize = 12.sp
                                )

//                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {

                                    }
//                                    Button(
//                                        onClick = {
//                                            SharedPrefHelper.removeFromHistory(context, item)
//                                            historyList =
//                                                SharedPrefHelper.getHistory(context).toMutableList()
//                                            coroutineScope.launch {
//                                                snackbarHostState.showSnackbar("Riwayat dihapus")
//                                            }
//                                        },
//                                        colors = ButtonDefaults.buttonColors(containerColor = BrutalRed)
//                                    ) {
//                                        Text("Hapus", color = Color.White)
//                                    }
                                    CustomButton(
                                        text = "Hapus",
                                        onClick = {
                                            SharedPrefHelper.removeFromHistory(context, item)
                                            historyList =
                                                SharedPrefHelper.getHistory(context).toMutableList()
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("Riwayat dihapus")
                                            }
                                        },
                                        height = 40.dp,
                                        widthPercentage = 0.3f,
                                        fontSize = 16.sp,
                                        backgroundColor = BrutalRed
                                    )
                                }
                            }
//                        Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}