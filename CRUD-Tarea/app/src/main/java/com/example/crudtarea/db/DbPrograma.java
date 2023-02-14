package com.example.crudtarea.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbPrograma extends DbHelper {
    Context context;
    public DbPrograma(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long agregarPrograma(int id_sistema_op,String nombre, String tipo, String fecha, Float peso) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = 0;
        try{
            ContentValues values = new ContentValues();
            values.put("id_sistema_op", id_sistema_op);
            values.put("nombre_programa", nombre);
            values.put("tipo_programa",tipo);
            values.put("fecha_instalacion",fecha);
            values.put("peso_programa",peso);

            id = db.insert("PROGRAMA",null, values);
        } catch (Exception e){
            e.toString();
        }
        return id;
    }
}
