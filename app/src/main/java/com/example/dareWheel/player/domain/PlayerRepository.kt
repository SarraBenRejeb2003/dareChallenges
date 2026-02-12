package com.example.dareWheel.player.domain

import com.example.dareWheel.player.domain.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun getAllPlayers(): Flow<List<PlayerEntity>>
    suspend fun insertPlayer(player: PlayerEntity)
}
