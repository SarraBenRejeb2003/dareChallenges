package com.example.dareWheel.game.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dareWheel.challenge.presentation.viewmodel.ChallengeViewModel
import com.example.dareWheel.player.presentation.viewmodel.PlayerViewModel

@Composable
fun GameSetupScreen(
    navController: NavController,
    playerViewModel: PlayerViewModel = viewModel(),
    challengeViewModel: ChallengeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val players by playerViewModel.players.collectAsState()
    var newPlayerText by remember { mutableStateOf("") }

    val challenges by challengeViewModel.challenges
    var newChallengeText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(purple, darkPurple)))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title Section
        Text(
            text = "🔥 DareDrop",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "“Drop the challenge. Face the chaos.”",
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Players Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            // Player Input
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Player Icon",
                        tint = purple,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    TextField(
                        value = newPlayerText,
                        onValueChange = { newPlayerText = it },
                        placeholder = { Text("Add Player", color = Color.Gray) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = purple,
                            focusedTextColor = purple,
                            unfocusedTextColor = purple
                        )
                    )
                    IconButton(
                        onClick = {
                            if (newPlayerText.isNotBlank()) {
                                playerViewModel.addPlayer(newPlayerText)
                                newPlayerText = ""
                            }
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .background(orange, CircleShape)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Player", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Player List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(players) { player ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier
                                .width(6.dp)
                                .fillMaxHeight()
                                .background(orange))
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Player Icon",
                                tint = purple,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Text(
                                text = player.name,
                                modifier = Modifier.weight(1f),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = darkPurple
                            )
                            IconButton(onClick = { playerViewModel.removePlayer(player) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Remove player", tint = Color.Gray)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Challenges Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            // Challenge Input
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Challenge Icon",
                        tint = purple,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    TextField(
                        value = newChallengeText,
                        onValueChange = { newChallengeText = it },
                        placeholder = { Text("Add Challenge", color = Color.Gray) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = purple,
                            focusedTextColor = purple,
                            unfocusedTextColor = purple
                        )
                    )
                    IconButton(
                        onClick = {
                            if (newChallengeText.isNotBlank()) {
                                challengeViewModel.addChallenge(newChallengeText)
                                newChallengeText = ""
                            }
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .background(orange, CircleShape)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Challenge", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Challenge List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(challenges) { challenge ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier
                                .width(6.dp)
                                .fillMaxHeight()
                                .background(orange))
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Challenge Icon",
                                tint = purple,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Text(
                                text = challenge.name,
                                modifier = Modifier.weight(1f),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = darkPurple
                            )
                            IconButton(onClick = { challengeViewModel.removeChallenge(challenge) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Remove challenge", tint = Color.Gray)
                            }
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Start Game Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(purple, orange)
                    )
                )
        ) {
            Button(
                onClick = {
                    navController.navigate("gameScreen")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Start Game Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "🎮 Start DareDrop",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}