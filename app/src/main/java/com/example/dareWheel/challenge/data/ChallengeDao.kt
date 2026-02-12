package com.example.dareWheel.challenge.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChallengeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenge(challenge: ChallengeDTO)

    @Query("SELECT * FROM challenges")
    suspend fun getAllChallenges(): List<ChallengeDTO>

    @Delete
    suspend fun deleteChallenge(challenge: ChallengeDTO)
}
