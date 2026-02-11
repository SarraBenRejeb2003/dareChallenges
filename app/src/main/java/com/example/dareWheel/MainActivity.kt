package com.example.dareWheel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dareWheel.screens.ChallengeListScreen
import com.example.dareWheel.screens.WheelScreen
import com.example.dareWheel.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "challengeList") {
        composable("challengeList") {
            ChallengeListScreen(navController = navController)
        }
        composable("wheelScreen/{challenges}") { backStackEntry ->
            val challenges = backStackEntry.arguments?.getString("challenges")?.split(",") ?: emptyList()
            WheelScreen(challenges = challenges)
        }
    }
}
