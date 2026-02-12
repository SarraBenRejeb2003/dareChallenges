package com.example.dareWheel.di

import android.content.Context
import com.example.dareWheel.challenge.data.ChallengeRepositoryImpl
import com.example.dareWheel.challenge.domain.ChallengeRepository
import com.example.dareWheel.data.AppDatabase
import com.example.dareWheel.player.data.PlayerRepositoryImpl
import com.example.dareWheel.player.domain.PlayerRepository

interface AppContainer {
    val challengeRepository: ChallengeRepository
    val playerRepository: PlayerRepository
    val viewModelFactory: ViewModelFactory
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    override val challengeRepository: ChallengeRepository by lazy {
        ChallengeRepositoryImpl(appDatabase.challengeDao)
    }

    override val playerRepository: PlayerRepository by lazy {
        PlayerRepositoryImpl(appDatabase.playerDao)
    }

    override val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(challengeRepository, playerRepository)
    }
}