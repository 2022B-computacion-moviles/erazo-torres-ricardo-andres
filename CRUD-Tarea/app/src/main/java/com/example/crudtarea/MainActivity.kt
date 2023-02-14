package com.example.crudtarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.crudtarea.db.DbHelper
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_main)
        var btnCrear = findViewById<Button>(R.id.btn_crear)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = getMenuInflater()
        inflater.inflate(R.menu.menu_principal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var boo = false
        when(item.itemId){
            R.id.menu_nuevo -> {
                boo = true
                nuevoRegistro(Registro::class.java)
            }
            else -> {
                boo = false
            }
        }
        return boo
    }

    fun nuevoRegistro (
        clase: Class<Registro>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}