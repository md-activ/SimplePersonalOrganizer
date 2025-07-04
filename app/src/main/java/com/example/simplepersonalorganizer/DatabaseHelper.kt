package com.example.simplepersonalorganizer.ui

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Users")
        onCreate(db)
    }

    fun insertUser(name: String, email: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("email", email)
        return db.insert("Users", null, contentValues) != -1L
    }

    fun getAdminUsers(): ArrayList<Pair<String, String>> {
        val list = ArrayList<Pair<String, String>>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users WHERE name = 'admin'", null)
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            list.add(Pair(name, email))
        }
        cursor.close()
        return list
    }

    fun getAllUsers(): ArrayList<Pair<String, String>> {
        val list = ArrayList<Pair<String, String>>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users", null)
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            list.add(Pair(name, email))
        }
        cursor.close()
        return list
    }
}
