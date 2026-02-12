package com.example.dareWheel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dareWheel.challenge.presentation.screens.WheelScreen
import com.example.dareWheel.challenge.presentation.viewmodel.ChallengeViewModel
import com.example.dareWheel.game.presentation.screens.GameScreen
import com.example.dareWheel.game.presentation.screens.GameSetupScreen
import com.example.dareWheel.game.presentation.viewmodel.GameViewModel
import com.example.dareWheel.player.presentation.viewmodel.PlayerViewModel
import com.example.dareWheel.ui.theme.MyApplicationTheme
import com.example.dareWheel.challenge.presentation.viewmodel.WheelViewModel

class MainActivity : ComponentActivity() {
    private val challengeViewModel: ChallengeViewModel by viewModels {
        (application as ChallengeApplication).container.viewModelFactory
    }
    private val wheelViewModel: WheelViewModel by viewModels {
        (application as ChallengeApplication).container.viewModelFactory
    }
    private val playerViewModel: PlayerViewModel by viewModels {
        (application as ChallengeApplication).container.viewModelFactory
    }
    private val gameViewModel: GameViewModel by viewModels {
        (application as ChallengeApplication).container.viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                AppNavigation(challengeViewModel, wheelViewModel, playerViewModel, gameViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(
    challengeViewModel: ChallengeViewModel,
    wheelViewModel: WheelViewModel,
    playerViewModel: PlayerViewModel,
    gameViewModel: GameViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "playerList") {
        composable("playerList") {
            GameSetupScreen(navController = navController, playerViewModel = playerViewModel, challengeViewModel = challengeViewModel)
        }
        composable("challengeList") {
            WheelScreen(wheelViewModel = wheelViewModel)
        }
        composable("wheelScreen") {
            WheelScreen(wheelViewModel = wheelViewModel)
        }
        composable("gameScreen") {
            GameScreen(gameViewModel = gameViewModel)
        }
    }
}