package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class LearnWordDbHelper(val context: Context, val name: String, val factory: CursorFactory?, val version: Int) :
    SQLiteOpenHelper(context, "app", factory, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS wordsTable (id INTEGER PRIMARY KEY, word TEXT, translate TEXT, isUsed INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE wordsTable")
        onCreate(db)
    }

//    fun AddWord(nameWord: String, translateWord: String) {
//        val values = ContentValues()
//        values.put("word", nameWord)
//        values.put("translate", translateWord)
//        values.put("isUsed", 0)
//
//
//        val db = this.writableDatabase
//        db.insert("wordsTable", null, values)
//        db.close()
//    }

    fun getWordCount(): Int {
        val db = this.readableDatabase
        val countQuery = "SELECT * FROM wordsTable"
        val cursor = db.rawQuery(countQuery, null)
        val count = cursor.count
        cursor.close()
        return count
    }

    fun AddWords() {
        val arrWord = arrayOf(
            "word", "слово",
            "world", "мир",
            "apple", "яблоко",
            "time", "время",
            "clock", "часы",
            "lime", "лайм",
            "cola", "кола",
            "klava", "клава",
            "son", "сон",
            "engl", "ингл",
        )
        val values = ContentValues()
        val count = 0
        var updatedRows: Long
        val db = this.writableDatabase
        for (word in arrWord) {
            if (count % 2 == 0) {
                values.put("word", word)
            } else {
                values.put("translate", word)
            }
            values.put("isUsed", 0)
            updatedRows = db.insert("wordsTable", null, values)
            println(updatedRows)
        }
        db.close()

    }

    fun ChangeIsUsed(idWord: Long): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("isUsed", 1)
        }

        val whereClause = "id = ?"
        val whereArgs = arrayOf(idWord.toString())

        val updatedRows = db.update("wordsTable", values, whereClause, whereArgs)
        db.close()

        return updatedRows > 0
    }

}

