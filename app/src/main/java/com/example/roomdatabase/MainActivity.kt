package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.roomdatabase.utils.User
import com.example.roomdatabase.utils.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var et_name : EditText
    lateinit var et_email : EditText
    lateinit var et_phone : EditText
    lateinit var et_city : EditText
    lateinit var btn_submit : Button
    lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_name = findViewById(R.id.et_name)
        et_email = findViewById(R.id.et_email)
        et_phone = findViewById(R.id.et_phone)
        et_city = findViewById(R.id.et_city)
        btn_submit = findViewById(R.id.btn_submit)

        userDatabase = Room.databaseBuilder(applicationContext,UserDatabase::class.java,"userDB").build()

        btn_submit.setOnClickListener {

            val name: String = et_name.text.toString()
            val email: String = et_email.text.toString()
            val phone: String = et_phone.text.toString()
            val city: String = et_city.text.toString()

            if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || city.isEmpty())
            {
                Toast.makeText(this,"Input cant be empty",Toast.LENGTH_LONG).show()
            }
            else{
                submitInfo(name,email,phone,city)
                val intent = Intent(this,UsersInfoActivity::class.java)
                startActivity(intent)
            }

        }

    }

    fun submitInfo(name : String, email : String, phone : String, city : String)
    {
        val user : User = User(0,name,email,phone,city)

        GlobalScope.launch {

             userDatabase.getUserDao().insertUser(user)

        }

    }
}