package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import com.example.roomdatabase.utils.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UsersInfoActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    lateinit var userAdapter : UserAdapter
    lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_info)

        rv = findViewById(R.id.rv)

        rv.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(emptyList())
        rv.adapter = userAdapter

        userDatabase = Room.databaseBuilder(applicationContext,UserDatabase::class.java,"userDB").build()



            val users = userDatabase.getUserDao().getUsers()
            users.observe(this@UsersInfoActivity, Observer { allUsers->
                if(allUsers.isNotEmpty())
                {
                    userAdapter = UserAdapter(allUsers)
                    rv.adapter = userAdapter
                }
            })
        }


}