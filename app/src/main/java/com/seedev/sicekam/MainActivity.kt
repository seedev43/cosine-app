package com.seedev.sicekam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.seedev.sicekam.ui.components.CustomButton
import com.seedev.sicekam.ui.navigation.NavGraph
import com.seedev.sicekam.ui.screen.HomeScreen
import com.seedev.sicekam.ui.theme.CustomColor1
import com.seedev.sicekam.ui.theme.CustomShadowColor
import com.seedev.sicekam.ui.theme.SiCeKamTheme

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
