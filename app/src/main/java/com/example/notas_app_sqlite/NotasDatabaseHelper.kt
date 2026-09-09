package com.example.notas_app_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasDatabaseHelper (context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null , DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "Create Table $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_DESCRIPTION TEXT"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
       val dropTableQuery =
           "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    companion object{
        private const val DATABASE_NAME = "notas.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "notas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "titulo"
        private const val COLUMN_DESCRIPTION = "descripcion"
    }
}