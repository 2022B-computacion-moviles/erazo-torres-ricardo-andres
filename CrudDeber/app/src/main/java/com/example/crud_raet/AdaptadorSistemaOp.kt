package com.example.crud_raet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorSistemaOp (
    val listaSistemaOp: MutableList<SistemaOp>,
    val listener: AdaptadorListener
): RecyclerView.Adapter<AdaptadorSistemaOp.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sistema_op,parent,false )
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sistemaOp = listaSistemaOp[position]

        holder.tvNombreSistemaOp.text = sistemaOp.nombre_sistema_op
        holder.tvTipoSistemaOp.text = sistemaOp.tipo_sistema_op
        holder.tvFechaSistemaOp.text = sistemaOp.fecha_lanzamiento
        holder.tvVersionOp.text = sistemaOp.version_sistema_op.toString()

        holder.cvSistemaOp.setOnClickListener{
            listener.onEditItemClick(sistemaOp)
        }
        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(sistemaOp)
        }
    }

    override fun getItemCount(): Int {
        return listaSistemaOp.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvSistemaOp = itemView.findViewById<CardView>(R.id.cvSistemaOp)
        val tvNombreSistemaOp = itemView.findViewById<TextView>(R.id.tvnombre_sistema_op)
        val tvTipoSistemaOp = itemView.findViewById<TextView>(R.id.tvtipo_sistema_op)
        val tvFechaSistemaOp = itemView.findViewById<TextView>(R.id.tvfecha_lanzamiento_sistema_op)
        val tvVersionOp = itemView.findViewById<TextView>(R.id.tvversion_sistema_op)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnDelete)
    }
}