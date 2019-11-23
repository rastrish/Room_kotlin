package com.example.room.data.roomdatabase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.data.roomdatabase.Entity.Word

@Dao
interface Dao {

    @Query("Select * from word_table ORDER BY word ASC")
    fun getWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word : Word)

    @Query("DELETE FROM word_table WHERE word = :deleteWord")
    suspend fun deleteWord(deleteWord : String)


    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}