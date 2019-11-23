package com.example.room

import androidx.lifecycle.LiveData
import com.example.room.data.roomdatabase.Dao.Dao
import com.example.room.data.roomdatabase.Entity.Word

class ApplicationRepository(private val dao: Dao)
{
    val allWords: LiveData<List<Word>> = dao.getWords()

    suspend fun insert(word: Word) {
        dao.insert(word)
    }

    suspend fun delete(deleteWord : String)
    {
        dao.deleteWord(deleteWord)
    }

    suspend fun deleteAll()
    {
        dao.deleteAll()
    }



}