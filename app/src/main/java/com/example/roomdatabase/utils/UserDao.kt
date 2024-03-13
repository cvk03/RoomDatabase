package com.example.roomdatabase.utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("delete from user")
    suspend fun deleteUsers()

    @Query("SELECT * from user")
    fun getUsers() : LiveData<List<User>>

}