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
        val db = this.writableDatabase
        val values = ContentValues()

        try {
            db.beginTransaction()

            for (i in arrWord.indices step 2) {
                values.clear()

                values.put("word", arrWord[i])
                values.put("translate", arrWord[i + 1])
                values.put("isUsed", 0)

                val updatedRows = db.insert("wordsTable", null, values)
                println(updatedRows)
            }

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
            db.close()
        }

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

    data class WordData(val id: Int, val word: String, val translate: String, val isUsed: Int)

    fun getWordData(idWord: Int): WordData? {
        val db = readableDatabase
        val query = "SELECT * FROM wordsTable WHERE id = $idWord"
        val cursor = db.rawQuery(query, null)

        var result: WordData? = null
        cursor?.use {
            if (it.moveToFirst()) {
                val idIndex = it.getColumnIndex("id")
                val wordIndex = it.getColumnIndex("word")
                val translateIndex = it.getColumnIndex("translate")
                val isUsedIndex = it.getColumnIndex("isUsed")

                val idValue = it.getInt(idIndex)
                val wordValue = it.getString(wordIndex)
                val translateValue = it.getString(translateIndex)
                val isUsedValue = it.getInt(isUsedIndex)

                result = WordData(idValue, wordValue, translateValue, isUsedValue)
            }
        }

        cursor?.close()
        db.close()

        return result
    }


}

