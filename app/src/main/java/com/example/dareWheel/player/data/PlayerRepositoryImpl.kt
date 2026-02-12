package com.example.dareWheel.player.data

import com.example.dareWheel.player.domain.PlayerEntity
import com.example.dareWheel.player.domain.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerRepositoryImpl(private val playerDao: PlayerDao) : PlayerRepository {

    override fun getAllPlayers(): Flow<List<PlayerEntity>> {
        return playerDao.getAllPlayers().map { list ->
            list.map { PlayerEntity(id = it.id.toString(), name = it.name) }
        }
    }

    override suspend fun insertPlayer(player: PlayerEntity) {
        playerDao.insert(PlayerDTO(name = player.name))
    }
}
