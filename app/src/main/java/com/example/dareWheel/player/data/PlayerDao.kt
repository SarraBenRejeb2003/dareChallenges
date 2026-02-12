package com.example.dareWheel.player.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert
    suspend fun insert(player: PlayerDTO)

    @Query("SELECT * FROM players")
    fun getAllPlayers(): Flow<List<PlayerDTO>>
}
