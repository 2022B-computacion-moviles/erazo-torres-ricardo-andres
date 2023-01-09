package com.example.movcompraet

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
class BListView : AppCompatActivity() {
    var arreglo:ArrayList<BEntrenador> = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1, //como se va ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAñadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAñadirListView.setOnClickListener{
            añadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llamamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del ArraylistSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{dialog,
                                            which ->
                //Al aceptar eliminar el registro
            }
        )
        builder.setNegativeButton("Cancelar",null)

        val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)
        val seleccionPrevia = booleanArrayOf(
            true, //lunes seleccionado
            false, //martes no seleccionado
            false, //miercoles no seleccionado
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {
                    dialog,
                    which,
                    isChecked -> "Dio clic en el item ${which}"
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun añadirEntrenador(adaptador:ArrayAdapter<BEntrenador>){
        arreglo.add(
            BEntrenador("Ejemplo","e@e.com")
        )
        adaptador.notifyDataSetChanged()
    }
}
/*
class BListView : AppCompatActivity() {
    //llenaremos el list.view con los datos del arreglo
    val arreglo:ArrayList<BEntrenador> = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_expandable_list_item_1, //Cómo se va a ver en XML
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView
            .setOnClickListener {
                anadirEntrenador(adaptador)
            }

        registerForContextMenu(listView)
    }//Fin onCreate

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar -> {
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar -> {
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog,
                    which ->
                //Al aceptar eliminar el registro
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(
            true, //Lunes seleccionado
            false, //Martes NO seleccionado
            false, //Miercoles NO seleccionado
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            { dialog,
                which,
                isChecked ->
                "Dio click en el item ${which}"
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun anadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(
            BEntrenador(
                "Ejemplo", "a@a.com"
            )
        )
        //actualizar la interfaz
        adaptador.notifyDataSetChanged()
    }
}
 */