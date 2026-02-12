package com.example.dareWheel.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dareWheel.challenge.domain.ChallengeEntity
import com.example.dareWheel.challenge.domain.ChallengeRepository
import com.example.dareWheel.challenge.domain.usecase.PickRandomChallengeUseCase
import com.example.dareWheel.player.domain.PlayerEntity
import com.example.dareWheel.player.domain.PlayerRepository
import com.example.dareWheel.player.domain.usecase.PickRandomPlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GameViewModel(
    private val challengeRepository: ChallengeRepository,
    private val playerRepository: PlayerRepository,
    private val pickRandomChallengeUseCase: PickRandomChallengeUseCase,
    private val pickRandomPlayerUseCase: PickRandomPlayerUseCase
) : ViewModel() {

    private val _selectedPlayer = MutableStateFlow<PlayerEntity?>(null)
    val selectedPlayer: StateFlow<PlayerEntity?> = _selectedPlayer

    private val _selectedChallenge = MutableStateFlow<ChallengeEntity?>(null)
    val selectedChallenge: StateFlow<ChallengeEntity?> = _selectedChallenge

    fun pickRandoms() {
        viewModelScope.launch {
            val players = playerRepository.getAllPlayers().first()
            val challenges = challengeRepository.getAllChallenges()

            _selectedPlayer.value = pickRandomPlayerUseCase(players)
            _selectedChallenge.value = pickRandomChallengeUseCase(challenges)
        }
    }
}