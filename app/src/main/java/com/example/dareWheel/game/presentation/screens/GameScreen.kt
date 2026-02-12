package com.example.dareWheel.game.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dareWheel.game.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val selectedPlayer by gameViewModel.selectedPlayer.collectAsState()
    val selectedChallenge by gameViewModel.selectedChallenge.collectAsState()

    LaunchedEffect(Unit) {
        gameViewModel.pickRandoms()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        selectedPlayer?.let {
            Text(text = "Player: ${it.name}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedChallenge?.let {
            Text(text = "Challenge: ${it.name}")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { gameViewModel.pickRandoms() }) {
            Text("Next Round")
        }
    }
}
