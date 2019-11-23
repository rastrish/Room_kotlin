package com.example.room.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.ApplicationRepository
import com.example.room.data.DatabaseService
import com.example.room.data.roomdatabase.Entity.Word
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository: ApplicationRepository
    // LiveData gives us updated words when they change.
    val allWords: LiveData<List<Word>>

    init {
        val dao = DatabaseService.getDatabase(application).Dao()
        repository = ApplicationRepository(dao)
        allWords = repository.allWords
    }

    fun insert(word : Word)
    {
        viewModelScope.launch {
            repository.insert(word)

        }
    }

    fun delete(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun deleteWord(deleteWord : String){
        viewModelScope.launch {
            repository.delete(deleteWord)
        }
    }


}