package com.example.simplepersonalorganizer.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.simplepersonalorganizer.ui.DatabaseHelper

class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this).apply {
            setPadding(16, 240, 16, 16) // Add spacing on top and sides
            clipToPadding = false
        }
        setContentView(listView)

        val db = DatabaseHelper(this)
        val allUsers = db.getAllUsers()
        val displayList = allUsers.map { "Name: ${it.first}, Email: ${it.second}" }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        listView.adapter = adapter
    }
}
