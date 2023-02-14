package com.example.crud_tarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud_tarea.R
import com.example.crud_tarea.db.DbHelper
import android.database.sqlite.SQLiteDatabase
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var btnCrear: Button? = null
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)
        btnCrear = findViewById(R.id.btn_crear)
        btnCrear.setOnClickListener(
            View, /* !!! Hit visitElement for element type: class org.jetbrains.kotlin.nj2k.tree.JKErrorExpression !!! */,
            view
        )
        run {
            val dbHelper = DbHelper(this@MainActivity)
            val db = dbHelper.writableDatabase
        }
    }
}