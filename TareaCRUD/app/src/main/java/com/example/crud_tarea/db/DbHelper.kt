package com.example.crud_tarea.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(
    contexto: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(
    contexto,
    name,
    factory,
    version
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaPrograma =
            """
                CREATE TABLE PROGRAMA 
                (
                   id_programa          INTEGER PRIMARY KEY AUTOINCREMENT,
                   id_sistema_op        INTEGER,
                   nombre_programa      VARCHAR(100),
                   tipo_programa        VARCHAR(100),
                   peso_programa        FLOAT,
                   fecha_instalacion_programa VARCHAR(100),
                   FOREIGN KEY ("id_sistema_op") REFERENCES SISTEMAOPERATIVO(id_sistema_op)
                );
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaPrograma)
        val scriptSQLCrearTablaSistemaOperativo =
            """
                create table SISTEMAOPERATIVO 
                (
                   id_sistema_op        INTEGER,
                   nombre_sistema_op    VARCHAR(100),
                   tipo_sistema_op      VARCHAR(100),
                   version_sistema_op   VARCHAR(100),
                   fecha_lanzamiento_sistema_op VARCHAR(100)
                ); 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaSistemaOperativo)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}