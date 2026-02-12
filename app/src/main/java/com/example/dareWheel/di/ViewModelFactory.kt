package com.example.dareWheel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dareWheel.challenge.domain.ChallengeRepository
import com.example.dareWheel.challenge.domain.usecase.PickRandomChallengeUseCase
import com.example.dareWheel.challenge.presentation.viewmodel.ChallengeViewModel
import com.example.dareWheel.challenge.presentation.viewmodel.WheelViewModel
import com.example.dareWheel.game.presentation.viewmodel.GameViewModel
import com.example.dareWheel.player.domain.PlayerRepository
import com.example.dareWheel.player.domain.usecase.PickRandomPlayerUseCase
import com.example.dareWheel.player.presentation.viewmodel.PlayerViewModel

class ViewModelFactory(
    private val challengeRepository: ChallengeRepository,
    private val playerRepository: PlayerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChallengeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChallengeViewModel(challengeRepository) as T
        }
        if (modelClass.isAssignableFrom(WheelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WheelViewModel(challengeRepository) as T
        }
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(playerRepository) as T
        }
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(
                challengeRepository,
                playerRepository,
                PickRandomChallengeUseCase(),
                PickRandomPlayerUseCase()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}