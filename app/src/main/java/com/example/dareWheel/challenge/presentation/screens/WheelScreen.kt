package com.example.dareWheel.challenge.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dareWheel.challenge.presentation.viewmodel.WheelViewModel
import com.example.darewheel.ui.theme.Pink40
import com.example.darewheel.ui.theme.Purple40

@Composable
fun WheelScreen(
    wheelViewModel: WheelViewModel = viewModel()
) {
    val challenges by wheelViewModel.challenges.collectAsState()
    val selectedChallenge by wheelViewModel.selectedChallenge.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Challenge Picker",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            if (challenges.isEmpty()) {
                Text(
                    "No challenges available.",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Purple40, Pink40)
                            )
                        )
                ) {
                    Button(
                        onClick = { wheelViewModel.pickRandomChallenge() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        Text(
                            "Pick a Random Challenge",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(32.dp))

                selectedChallenge?.let { challenge ->
                    Card(
                        modifier = Modifier.padding(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Your Challenge:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = challenge.name,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Purple40
                            )
                        }
                    }
                }
            }
        }
    }
}