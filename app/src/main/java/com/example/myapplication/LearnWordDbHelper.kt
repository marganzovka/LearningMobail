package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class LearnWordDbHelper(val context: Context, val name: String, val factory: CursorFactory?, val version: Int) :
    SQLiteOpenHelper(context, "app", factory, 1) {

    // Создание бд
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS wordsTable (id INTEGER PRIMARY KEY, word TEXT, translate TEXT, isUsed INTEGER)")
    }

    // Обновление бд
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE wordsTable")
        onCreate(db)
    }


    // Метод возвращающий количество слов в бд
    fun getWordCount(): Int {
        val db = this.readableDatabase
        val countQuery = "SELECT * FROM wordsTable"
        val cursor = db.rawQuery(countQuery, null)
        val count = cursor.count
        cursor.close()
        return count
    }

    fun getWordNotLearnedCount(): Int {
        val db = this.readableDatabase
        val countQuery = "SELECT * FROM wordsTable WHERE isUsed = 0"
        val cursor = db.rawQuery(countQuery, null)
        val count = cursor.count
        cursor.close()
        return count
    }

    // Метод добавляющий слова с переводом в бд
    fun AddWords() {
        val arrWord = arrayOf(
            "Code", "Код",
            "Programming", "Программирование",
            "Software",  "Программное обеспечение",
            "Debugging", "Отладка",
            "Algorithm", "Алгоритм",
            "Function", "Функция",
            "Variable",  "Переменная",
            "Loop", "Цикл",
            "Database", "База данных",
            "Framework", "Фреймворк",
            "Version Control", "Система контроля версий",
            "Repository", "Репозиторий",
            "Git", "Гит",
            "API",  "Интерфейс программирования приложений",
            "Scripting", "Сценарный язык программирования",
            "Debug", "Отладка",
            "IDE",  "Интегрированная среда разработки",
            "Compiler", "Компилятор",
            "Syntax",  "Синтаксис",
            "Class",  "Класс",
            "Object", "Объект",
            "Interface", "Интерфейс",
            "Bug", "Баг, ошибка",
            "Deployment", "Развертывание",
            "Agile", "Гибкий метод разработки",
            "Framework", "Фреймворк",
            "Front-end", "Разработка интерфейса",
            "Back-end", "Разработка серверной части",
            "Code Review", "Проверка кода",
            "Documentation", "Документация"
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

    fun RestartIsUsed() {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("isUsed", 0)
        }

        val updatedRows = db.update("wordsTable", values, null, null)
        db.close()

        println("Updated rows: $updatedRows")
    }


    // Метод, меняющий значение "использовалось ли слово ранее" на true
    fun ChangeIsUsed(idWord: Int?): Boolean {
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

    // Класс состоящий из одного элемента бд
    data class WordData(val id: Int, val word: String, val translate: String, val isUsed: Int)

    // Метод, возращающий объект в котором доступны все поля элемента из бд
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

