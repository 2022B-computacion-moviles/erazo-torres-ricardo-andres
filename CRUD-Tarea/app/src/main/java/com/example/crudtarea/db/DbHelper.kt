package com.example.crudtarea.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.example.crudtarea.db.DbHelper
import android.database.sqlite.SQLiteDatabase

open class DbHelper(context: Context?) :SQLiteOpenHelper(
    context,
    "Tarea-Crud.db",
    null,
    1
){
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE SISTEMA_OP(" +
                    "id_sistema_op INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_sistema_op TEXT NOT NULL," +
                    "version_sistema_op FLOAT NOT NULL," +
                    "tipo_sistema_op TEXT NOT NULL," +
                    "fecha_lanzamiento TEXT NOT NULL" +
                    ");"
        )
        sqLiteDatabase.execSQL(
            "CREATE TABLE PROGRAMA(" +
                    "id_programa INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_sistema_op INTEGER,"+
                    "nombre_programa TEXT NOT NULL," +
                    "tipo_programa TEXT NOT NULL," +
                    "fecha_instalación TEXT NOT NULL," +
                    "peso_programa FLOAT NOT NULL," +
                    "FOREIGN KEY (id_sistema_op) REFERENCES " +
                    "SISTEMA_OP(id_sistema_op) " +
                    ");"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE PROGRAMA")
        sqLiteDatabase.execSQL("DROP TABLE SISTEMA_OP")
        onCreate(sqLiteDatabase)
    }
}