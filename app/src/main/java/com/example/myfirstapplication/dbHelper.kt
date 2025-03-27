package com.example.myfirstapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_Name, null, DB_Version) {

    companion object {
        private const val DB_Name = "UserDb"
        private const val DB_Version = 1

        const val Table_Name = "User"
        const val id = "id"
        const val Name = "name"
        const val Dob = "dob"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $Table_Name(
                $id INTEGER PRIMARY KEY AUTOINCREMENT,
                $Name TEXT,
                $Dob TEXT
            )
        """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $Table_Name")
        onCreate(db)
    }

    fun addUser(name: String, dob: String): Long {
        val values = ContentValues().apply {
            put(Name, name)
            put(Dob, dob)
        }
        val db = this.writableDatabase
        val rowId = db.insert(Table_Name, null, values)
        db.close()
        return rowId
    }

    fun getUser(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $Table_Name", null)
    }
}
