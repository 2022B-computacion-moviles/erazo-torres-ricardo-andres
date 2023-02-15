package com.example.crud_raet

import androidx.room.*

@Dao
interface DaoSistemaOp {
    //Se implementan las CRUD para un sistema operativo
    @Query("SELECT * FROM SISTEMA_OP")
    suspend fun leerSistemasOp(): MutableList<SistemaOp>

    @Insert
    suspend fun crearSistemaOp(sistemaOp: SistemaOp)

    @Update
    suspend fun actualizarSistemaOp(sistemaOp: SistemaOp)

    @Delete
    suspend fun borrarSistemaOp(sistemaOp: SistemaOp)
}