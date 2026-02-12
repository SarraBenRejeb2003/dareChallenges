package com.example.dareWheel.challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dareWheel.challenge.domain.ChallengeEntity
import com.example.dareWheel.challenge.domain.ChallengeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WheelViewModel(private val challengeRepository: ChallengeRepository) : ViewModel() {

    private val _challenges = MutableStateFlow<List<ChallengeEntity>>(emptyList())
    val challenges: StateFlow<List<ChallengeEntity>> = _challenges

    private val _selectedChallenge = MutableStateFlow<ChallengeEntity?>(null)
    val selectedChallenge: StateFlow<ChallengeEntity?> = _selectedChallenge

    init {
        loadChallenges()
    }

    private fun loadChallenges() {
        viewModelScope.launch {
            _challenges.value = challengeRepository.getAllChallenges()
        }
    }

    fun pickRandomChallenge() {
        if (_challenges.value.isNotEmpty()) {
            _selectedChallenge.value = _challenges.value.random()
        }
    }
}