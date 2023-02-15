package com.example.crud_raet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.crud_raet.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdaptadorListener {

    lateinit var binding: ActivityMainBinding

    var listaSistemaOp: MutableList<SistemaOp> = mutableListOf()
    lateinit var adaptador: AdaptadorSistemaOp

    lateinit var room: DbSistemaOp
    lateinit var sistemaOp: SistemaOp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSistemasOp.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this,DbSistemaOp::class.java, "dbSistemaOp").build()

        leerSistemasOp(room)

        binding.btnAddUpdate.setOnClickListener {
            if (binding.nombreSistemaOp.text.isNullOrEmpty()
                || binding.tipoSistemaOp.text.isNullOrEmpty()
                || binding.fechaLanzamientoSistemaOp.text.isNullOrEmpty()
                || binding.versionSistemaOp.text.isNullOrEmpty()
                    ) {
                Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.btnAddUpdate.text.equals("agregar")){
                sistemaOp = SistemaOp(
                    listaSistemaOp.size-1,
                    binding.nombreSistemaOp.text.toString().trim(),
                    binding.tipoSistemaOp.text.toString().trim(),
                    binding.fechaLanzamientoSistemaOp.text.toString().trim(),
                    binding.versionSistemaOp.text.toString().toFloat()
                )
                crearSistemaOp(room,sistemaOp)
            } else if(binding.btnAddUpdate.text.equals("actualizar")){
                sistemaOp.nombre_sistema_op = binding.nombreSistemaOp.text.toString().trim()
                sistemaOp.tipo_sistema_op = binding.tipoSistemaOp.text.toString().trim()
                sistemaOp.fecha_lanzamiento = binding.fechaLanzamientoSistemaOp.text.toString().trim()
                sistemaOp.version_sistema_op = binding.versionSistemaOp.text.toString().toFloat()

                actualizarSistemaOp(room, sistemaOp)
            }
        }
    }
    fun leerSistemasOp(room: DbSistemaOp){
        lifecycleScope.launch{
            listaSistemaOp = room.daoSistemaOp().leerSistemasOp()
            adaptador = AdaptadorSistemaOp(listaSistemaOp, this@MainActivity)
            binding.rvSistemasOp.adapter = adaptador
        }
    }

    fun crearSistemaOp(room: DbSistemaOp,sistemaOp: SistemaOp){
        lifecycleScope.launch{
            room.daoSistemaOp().crearSistemaOp(sistemaOp)
            leerSistemasOp(room)
            limpiarCampos()
        }
    }

    fun actualizarSistemaOp(room: DbSistemaOp,sistemaOp: SistemaOp){
        lifecycleScope.launch{
            room.daoSistemaOp().actualizarSistemaOp(
                sistemaOp
            )
            leerSistemasOp(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos(){
        sistemaOp.nombre_sistema_op = ""
        sistemaOp.tipo_sistema_op = ""
        sistemaOp.fecha_lanzamiento = ""
        sistemaOp.version_sistema_op = 0F
        binding.nombreSistemaOp.setText("")
        binding.tipoSistemaOp.setText("")
        binding.fechaLanzamientoSistemaOp.setText("")
        binding.versionSistemaOp.setText("")

        if (binding.btnAddUpdate.text.equals("actualizar")){
            binding.btnAddUpdate.setText("agregar")
        }
    }
    override fun onEditItemClick(sistemaOp: SistemaOp) {
        binding.btnAddUpdate.setText("actualizar")
        this.sistemaOp = sistemaOp
        binding.nombreSistemaOp.setText(this.sistemaOp.nombre_sistema_op)
        binding.tipoSistemaOp.setText(this.sistemaOp.tipo_sistema_op)
        binding.fechaLanzamientoSistemaOp.setText(this.sistemaOp.fecha_lanzamiento)
        binding.versionSistemaOp.setText(this.sistemaOp.version_sistema_op.toString())
    }

    override fun onDeleteItemClick(sistemaOp: SistemaOp) {
        lifecycleScope.launch {
            room.daoSistemaOp().borrarSistemaOp(sistemaOp)
            adaptador.notifyDataSetChanged()
            leerSistemasOp(room)
        }
    }
}