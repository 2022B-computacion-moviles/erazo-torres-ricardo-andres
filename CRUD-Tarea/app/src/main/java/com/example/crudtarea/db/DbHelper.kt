package com.example.crudtarea.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.example.crudtarea.db.DbHelper
import android.database.sqlite.SQLiteDatabase

class DbHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE " + TABLE_PROGRAMA + "(" +
                    "id_programa INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_programa TEXT NOT NULL," +
                    "tipo_programa TEXT NOT NULL," +
                    "fecha_instalación TEXT NOT NULL," +
                    "peso_programa FLOAT NOT NULL," +
                    "FOREIGN KEY (id_sistema_op) REFERENCES " + TABLE_SISTEMA_OP +
                    "(id_sistemas_op) )"
        )
        sqLiteDatabase.execSQL(
            "CREATE TABLE " + TABLE_SISTEMA_OP + "(" +
                    "id_sistema_op INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_sistema_op TEXT NOT NULL," +
                    "version_programa FLOAT NOT NULL," +
                    "tipo_sistema_op TEXT NOT NULL," +
                    "fecha_lanzamiento TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PROGRAMA)
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SISTEMA_OP)
        onCreate(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NOMBRE = "tareaCrud.db"
        private const val TABLE_PROGRAMA = "PROGRAMA"
        private const val TABLE_SISTEMA_OP = "SISTEMA_OP"
    }
}