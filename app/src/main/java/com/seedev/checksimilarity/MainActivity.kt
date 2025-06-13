package com.seedev.checksimilarity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.seedev.checksimilarity.ui.navigation.NavGraph
import com.seedev.checksimilarity.ui.theme.SiCeKamTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SiCeKamTheme (darkTheme = false) {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}
