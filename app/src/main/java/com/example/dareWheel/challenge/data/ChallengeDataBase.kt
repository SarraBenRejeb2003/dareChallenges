package com.example.dareWheel.challenge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ChallengeDTO::class],
    version = 1
)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract val challengedao: ChallengeDao

    companion object {
        @Volatile
        private var INSTANCE: ChallengeDatabase? = null

        fun getDatabase(context: Context): ChallengeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChallengeDatabase::class.java,
                    "challenge.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}