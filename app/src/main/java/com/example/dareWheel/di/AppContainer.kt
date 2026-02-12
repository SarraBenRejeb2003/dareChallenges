package com.example.dareWheel.di

import android.content.Context
import androidx.room.Room
import com.example.dareWheel.challenge.data.ChallengeDatabase
import com.example.dareWheel.challenge.data.ChallengeRepositoryImpl
import com.example.dareWheel.challenge.domain.ChallengeRepository

interface AppContainer {
    val challengeRepository: ChallengeRepository
    val viewModelFactory: ViewModelFactory
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val challengeRepository: ChallengeRepository by lazy {
        ChallengeRepositoryImpl(ChallengeDatabase.getDatabase(context).challengedao)
    }

    override val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(challengeRepository)
    }
}