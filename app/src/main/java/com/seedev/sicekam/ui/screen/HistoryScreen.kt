package com.seedev.sicekam.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seedev.sicekam.model.SimilarityResult
import com.seedev.sicekam.ui.theme.BrutalBrown
import com.seedev.sicekam.ui.theme.BrutalGreen
import com.seedev.sicekam.ui.theme.BrutalRed
import com.seedev.sicekam.utils.SharedPrefHelper
import kotlinx.coroutines.launch

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
                title = { Text("Riwayat Kemiripan", color = BrutalBrown) },
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
                            historyList.clear()
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
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Kalimat 1: ${item.originalText1}", fontSize = 14.sp)
                            Text("Kalimat 2: ${item.originalText2}", fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Kemiripan: ${item.similarityPercent}", fontSize = 14.sp)
                            Text("Status: ${item.similarityStatus}", fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    SharedPrefHelper.removeFromHistory(context, item)
                                    historyList = SharedPrefHelper.getHistory(context).toMutableList()
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Riwayat dihapus")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = BrutalRed),
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("Hapus", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}