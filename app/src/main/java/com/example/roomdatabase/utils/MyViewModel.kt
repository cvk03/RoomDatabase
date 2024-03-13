package com.example.roomdatabase.utils

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room

class MyViewModel (val context :Context) : ViewModel() {

    var userDatabase : UserDatabase
    init {
     userDatabase = Room.databaseBuilder(context,UserDatabase::class.java,"userDB").build()
    }

    fun getUsers() : LiveData<List<User>>
    {
        return userDatabase.getUserDao().getUsers()
    }

    suspend fun deleteUsers()
    {
        userDatabase.getUserDao().deleteUsers()
    }


}