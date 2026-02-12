package com.example.dareWheel.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dareWheel.player.domain.PlayerEntity
import com.example.dareWheel.player.domain.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(private val playerRepository: PlayerRepository) : ViewModel() {

    private val _players = MutableStateFlow<List<PlayerEntity>>(emptyList())
    val players: StateFlow<List<PlayerEntity>> = _players

    init {
        loadPlayers()
    }

    private fun loadPlayers() {
        viewModelScope.launch {
            playerRepository.getAllPlayers().collect {
                _players.value = it
            }
        }
    }

    fun addPlayer(name: String) {
        viewModelScope.launch {
            playerRepository.insertPlayer(PlayerEntity(id = "", name = name))
        }
    }

    fun removePlayer(player: PlayerEntity) {
        viewModelScope.launch {
            playerRepository.deletePlayer(player)
        }
    }
}
