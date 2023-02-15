package com.example.crud_raet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SISTEMA_OP")
data class SistemaOp(
    @PrimaryKey(autoGenerate = true)
        var id_sistema_op: Int = 0,
    @ColumnInfo(name = "nombre_sistema_op") var nombre_sistema_op: String,
    @ColumnInfo(name = "tipo_sistema_op") var tipo_sistema_op: String,
    @ColumnInfo(name = "fecha_lanzamiento") var fecha_lanzamiento: String,
    @ColumnInfo(name = "version_sistema_op") var version_sistema_op: Float
)