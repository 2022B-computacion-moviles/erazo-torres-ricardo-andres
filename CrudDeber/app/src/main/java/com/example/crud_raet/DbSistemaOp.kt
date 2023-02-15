package com.example.crud_raet

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SistemaOp::class],
    version = 1
)
abstract class DbSistemaOp: RoomDatabase() {
    abstract fun daoSistemaOp(): DaoSistemaOp
}