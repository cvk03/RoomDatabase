package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import com.example.roomdatabase.utils.MyViewModel
import com.example.roomdatabase.utils.MyViewModelFactory
import com.example.roomdatabase.utils.User
import com.example.roomdatabase.utils.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UsersInfoActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    lateinit var userAdapter : UserAdapter
    lateinit var userDatabase: UserDatabase
    lateinit var btn_delete : Button
    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_info)

        rv = findViewById(R.id.rv)
        btn_delete = findViewById(R.id.btn_delete)

        rv.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(emptyList())
        rv.adapter = userAdapter


        myViewModel = ViewModelProvider(this,MyViewModelFactory(this)).get(MyViewModel::class.java)


            val users = myViewModel.getUsers()

           users.observe(this@UsersInfoActivity, Observer { allUsers->
            if(allUsers.isNotEmpty())
            {
                userAdapter = UserAdapter(allUsers)
                rv.adapter = userAdapter
            }
        })

            btn_delete.setOnClickListener {

            GlobalScope.launch {
                myViewModel.deleteUsers()

            }
                userAdapter = UserAdapter(emptyList())
                rv.adapter = userAdapter
                Toast.makeText(this,"All users deleted successfully!",Toast.LENGTH_LONG).show()
            }


        }

    fun check(users : LiveData<List<User>>)
    {users.observe(this@UsersInfoActivity, Observer { allUsers->
        if(allUsers.isNotEmpty())
        {
            userAdapter = UserAdapter(allUsers)
            rv.adapter = userAdapter
        }
    })}


}