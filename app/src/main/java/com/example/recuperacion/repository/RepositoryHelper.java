package com.example.recuperacion.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RepositoryHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "Restaurante";

    // Table columns
    public static final String FECHA = "_id";
    public static final String NOMBRE = "nombre";
    public static final String TELEFONO = "telefono";
    public static final String COMENSALES = "comensales";

    // Database Information
    static final String DB_NAME = "Restaurantes";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + NOMBRE
            + " TEXT NOT NULL, " + FECHA + " TEXT , " + COMENSALES + " INTEGER , " + TELEFONO + " TEXT);";

    public RepositoryHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
