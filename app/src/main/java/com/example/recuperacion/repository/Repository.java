package com.example.recuperacion.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recuperacion.Model.Reserva;

import java.util.ArrayList;

public class Repository {

    private RepositoryHelper dbHelper;

    private Context context;

    private MutableLiveData<ArrayList<Reserva>> reservaList;
    private SQLiteDatabase database;
    private ArrayList<Reserva> reservaArrayList;

    public Repository(Context c) {
        context = c;
    }

    public void open() throws SQLException {
        dbHelper = new RepositoryHelper(context);
        database = dbHelper.getWritableDatabase();
        reservaList = new MutableLiveData<>();
        reservaArrayList = new ArrayList<>();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Reserva reserva) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(RepositoryHelper.NOMBRE, reserva.getNombre());
        contentValue.put(RepositoryHelper.FECHA, reserva.getFecha());
        contentValue.put(RepositoryHelper.COMENSALES, reserva.getPersonas());
        contentValue.put(RepositoryHelper.TELEFONO, reserva.getTelefono());
        database.insert(RepositoryHelper.TABLE_NAME, null, contentValue);
        System.out.println(1);
    }

    public Cursor fetch() {
        String[] columns = new String[] { RepositoryHelper.NOMBRE, RepositoryHelper.FECHA, RepositoryHelper.COMENSALES, RepositoryHelper.TELEFONO};
        Cursor cursor = database.query(RepositoryHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        System.out.println(cursor.getCount());
        for (int i = 0; i<cursor.getCount(); i++) {
            reservaArrayList.add(new Reserva(cursor.getString(1),cursor.getInt(2),"",cursor.getString(0),cursor.getString(3)));
        }
        System.out.println(reservaArrayList.size());
        reservaList.postValue(reservaArrayList);
        return cursor;
    }

    public LiveData<ArrayList<Reserva>> getReservaLive() {
        return reservaList;
    }

}

