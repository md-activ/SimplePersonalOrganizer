package com.example.simplepersonalorganizer.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplepersonalorganizer.R

class MainActivity : AppCompatActivity() {
    lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.editName)
        val emailInput = findViewById<EditText>(R.id.editEmail)
        val saveBtn = findViewById<Button>(R.id.btnSave)
        val viewAllBtn = findViewById<Button>(R.id.btnViewAll)

        db = DatabaseHelper(this)

        saveBtn.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            if (db.insertUser(name, email)) {
                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()
                nameInput.text.clear()
                emailInput.text.clear()
            } else {
                Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show()
            }
        }

        viewAllBtn.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}
