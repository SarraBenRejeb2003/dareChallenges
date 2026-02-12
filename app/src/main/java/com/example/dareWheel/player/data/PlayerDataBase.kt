package com.example.dareWheel.player.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PlayerDTO::class],
    version = 1
)
abstract class PlayerDatabase : RoomDatabase() {
    abstract val playerdao: PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getDatabase(context: Context): PlayerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerDatabase::class.java,
                    "player.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}