package com.example.dareWheel.challenge.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dareWheel.challenge.presentation.viewmodel.ChallengeViewModel

@Composable
fun ChallengeListScreen(
    navController: NavController,
    challengeViewModel: ChallengeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val challenges by challengeViewModel.challenges
    var newChallengeText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = newChallengeText,
                onValueChange = { newChallengeText = it },
                label = { Text("Enter a new challenge") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newChallengeText.isNotBlank()) {
                        challengeViewModel.addChallenge(newChallengeText)
                        newChallengeText = ""
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (challenges.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(challenges) { challenge ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = challenge.name,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        } else {
            // Fills the space to push the button to the bottom
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("wheelScreen")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Let's Play")
        }
    }
}