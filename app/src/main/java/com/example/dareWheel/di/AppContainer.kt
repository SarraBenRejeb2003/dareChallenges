package com.example.dareWheel.di

import android.content.Context
import com.example.dareWheel.challenge.data.ChallengeDatabase
import com.example.dareWheel.challenge.data.ChallengeRepositoryImpl
import com.example.dareWheel.challenge.domain.ChallengeRepository
import com.example.dareWheel.player.data.PlayerDatabase
import com.example.dareWheel.player.data.PlayerRepositoryImpl
import com.example.dareWheel.player.domain.PlayerRepository

interface AppContainer {
    val challengeRepository: ChallengeRepository
    val playerRepository: PlayerRepository
    val viewModelFactory: ViewModelFactory
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val challengeRepository: ChallengeRepository by lazy {
        ChallengeRepositoryImpl(ChallengeDatabase.getDatabase(context).challengedao)
    }

    override val playerRepository: PlayerRepository by lazy {
        PlayerRepositoryImpl(PlayerDatabase.getDatabase(context).playerdao)
    }

    override val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(challengeRepository, playerRepository)
    }
}