package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.utils.User

class UserAdapter(val users : List<User>) : RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_user,parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
      return users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var curr_user = users[position]
        holder.tv_name.text = "Name : "+curr_user.name
        holder.tv_email.text = "Email : " + curr_user.email
        holder.tv_phone.text = "Phone : " + curr_user.phone
        holder.tv_city.text = "City : " + curr_user.city
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var tv_name = itemView.findViewById<TextView>(R.id.tv_name)
    var tv_email = itemView.findViewById<TextView>(R.id.tv_email)
    var tv_phone = itemView.findViewById<TextView>(R.id.tv_phone)
    var tv_city = itemView.findViewById<TextView>(R.id.tv_city)
}