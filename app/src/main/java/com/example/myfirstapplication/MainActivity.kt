package com.example.myfirstapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private var userList = mutableListOf<UserModel>() // Stores users, but RecyclerView starts empty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etDob = findViewById<EditText>(R.id.etDob)
        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnDisplay = findViewById<Button>(R.id.btnDisplay)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = UserAdapter(userList) { position ->
            userList.removeAt(position) // Remove user
            adapter.notifyItemRemoved(position)
            Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnInsert.setOnClickListener {
            val name = etName.text.toString().trim()
            val dob = etDob.text.toString().trim()

            if (name.isNotEmpty() && dob.isNotEmpty()) {
                val newUser = UserModel(userList.size + 1, name, dob)
                userList.add(newUser) // Store in list but do NOT update RecyclerView yet
                Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show()
                etName.text.clear()
                etDob.text.clear()
            } else {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
            }
        }

        btnDisplay.setOnClickListener {
            adapter.notifyDataSetChanged() // Only update RecyclerView when "Display" is clicked
        }
    }
}
