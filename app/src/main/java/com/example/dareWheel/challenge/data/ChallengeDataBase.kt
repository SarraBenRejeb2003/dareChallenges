package com.example.dareWheel.challenge.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ChallengeDTO::class],
    version = 1
)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract val challengedao: ChallengeDao

}