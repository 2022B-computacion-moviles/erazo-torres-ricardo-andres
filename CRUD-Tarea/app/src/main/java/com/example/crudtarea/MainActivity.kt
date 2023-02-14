package com.example.crudtarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudtarea.R
import com.example.crudtarea.db.DbHelper
import android.database.sqlite.SQLiteDatabase
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var btnCrear: Button? = null
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)
        btnCrear = findViewById(R.id.btn_crear)
        btnCrear.setOnClickListener(View.OnClickListener {
            val dbHelper = DbHelper(this@MainActivity)
            val db = dbHelper.writableDatabase
            if (db != null) {
                Toast.makeText(
                    this@MainActivity,
                    "BASE DE DATOS CREADA CON ÉXITO",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "ERROR: BASE DE DATOS NO CREADA!!!!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}