package com.example.simplepersonalorganizer.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this)
        setContentView(listView)

        val db = DatabaseHelper(this)
        val adminUsers = db.getAdminUsers()
        val displayList = adminUsers.map { "Name: ${it.first}, Email: ${it.second}" }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        listView.adapter = adapter
    }
}
