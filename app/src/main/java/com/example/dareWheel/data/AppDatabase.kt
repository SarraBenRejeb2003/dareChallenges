package com.example.dareWheel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dareWheel.challenge.data.ChallengeDTO
import com.example.dareWheel.challenge.data.ChallengeDao
import com.example.dareWheel.player.data.PlayerDTO
import com.example.dareWheel.player.data.PlayerDao

@Database(
    entities = [ChallengeDTO::class, PlayerDTO::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val challengeDao: ChallengeDao
    abstract val playerDao: PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
