package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.data.roomdatabase.Dao.Dao
import com.example.room.data.roomdatabase.Entity.Word

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class DatabaseService : RoomDatabase() {

    abstract fun Dao(): Dao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DatabaseService? = null

        fun getDatabase(context: Context): DatabaseService {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}