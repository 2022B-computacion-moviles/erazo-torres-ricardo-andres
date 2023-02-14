package com.example.crudtarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.crudtarea.db.DbHelper
import com.example.crudtarea.db.DbPrograma
import org.w3c.dom.Text

class Registro : AppCompatActivity() {

    var txtNombre = findViewById<EditText>(R.id.txt_nombre_programa)
    var txtTipo = findViewById<EditText>(R.id.txt_tipo_programa)
    var txtPeso = findViewById<EditText>(R.id.txt_peso_programa)
    var txtfecha = findViewById<EditText>(R.id.txt_fecha_instalacion)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        var btnAgregar = findViewById<Button>(R.id.btn_agregar)

        btnAgregar.setOnClickListener(View.OnClickListener {
            val dbPrograma = DbPrograma(this@Registro)

            dbPrograma.agregarPrograma(0,
                txtNombre.text.toString(),txtTipo.text.toString(),
                txtfecha.text.toString(),txtPeso.text.toString().toFloat()
            )
            if (dbPrograma != null) {
                Toast.makeText(
                    this@Registro,
                    "Programa agregado con éxito",
                    Toast.LENGTH_LONG
                ).show()
                limpiar()
            } else {
                Toast.makeText(
                    this@Registro,
                    "ERROR: Programa NO agregado!!!!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
    fun limpiar() {
        txtNombre.setText("")
        txtTipo.setText("")
        txtfecha.setText("")
        txtPeso.setText(0)
    }
}