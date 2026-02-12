package com.example.dareWheel.screens

import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.dareWheel.challenge.data.ChallengeDTO
import com.example.dareWheel.challenge.data.ChallengeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ChallengeListScreen(navController: NavController, modifier: Modifier = Modifier) {
    var challenges by remember { mutableStateOf(emptyList<ChallengeDTO>()) }
    var newChallengeText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val db = remember {
        Room.databaseBuilder(
            context.applicationContext,
            ChallengeDatabase::class.java, "challenge.db"
        ).build()
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            challenges = db.challengedao.getAllChallenges()
        }
    }

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
                        coroutineScope.launch(Dispatchers.IO) {
                            val newChallenge = ChallengeDTO(
                                name = newChallengeText,
                                id = 0
                            )
                            db.challengedao.insertChallenge(newChallenge)
                            challenges = db.challengedao.getAllChallenges()
                            newChallengeText = ""
                        }
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