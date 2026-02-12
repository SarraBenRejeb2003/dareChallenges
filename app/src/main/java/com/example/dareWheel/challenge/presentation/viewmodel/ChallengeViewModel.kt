package com.example.dareWheel.challenge.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dareWheel.challenge.domain.ChallengeEntity
import com.example.dareWheel.challenge.domain.ChallengeRepository
import kotlinx.coroutines.launch

class ChallengeViewModel(private val challengeRepository: ChallengeRepository) : ViewModel() {

    val challenges = mutableStateOf<List<ChallengeEntity>>(emptyList())

    init {
        loadChallenges()
    }

    private fun loadChallenges() {
        viewModelScope.launch {
            challenges.value = challengeRepository.getAllChallenges()
        }
    }

    fun addChallenge(name: String) {
        viewModelScope.launch {
            challengeRepository.addChallenge(ChallengeEntity(id = "", name = name))
            loadChallenges()
        }
    }
}