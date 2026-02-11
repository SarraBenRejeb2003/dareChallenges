package com.example.dareWheel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dareWheel.Challenge.data.Challenge

@Composable
fun ChallengeListScreen(navController: NavController, modifier: Modifier = Modifier) {
    var challenges by remember { mutableStateOf(mutableListOf(
        Challenge("Do 10 pushups"),
        Challenge("Sing a song out loud"),
        Challenge("Tell a joke"),
        Challenge("Do a little dance"),
        Challenge("Talk in a funny voice for 1 minute"),
        Challenge("Try to lick your elbow"),
        Challenge("Balance a spoon on your nose for 10 seconds"),
        Challenge("Make a funny face and keep it for 1 minute"),
    )) }
    var newChallengeText by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = newChallengeText,
                onValueChange = { newChallengeText = it },
                label = { Text("Enter a new challenge") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newChallengeText.isNotBlank()) {
                        challenges = (challenges + Challenge(newChallengeText)).toMutableList()
                        newChallengeText = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(challenges) { challenge ->
                Text(text = challenge.name, modifier = Modifier.padding(vertical = 4.dp))
            }
        }

        Button(onClick = { 
            val challengeNames = challenges.joinToString(",") { it.name }
            navController.navigate("wheelScreen/$challengeNames")
        }) {
            Text("Let's Play")
        }
    }
}