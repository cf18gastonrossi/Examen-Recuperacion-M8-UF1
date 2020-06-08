package com.example.recuperacion.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recuperacion.Model.Plato;
import com.example.recuperacion.Model.Reserva;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Repository {

    private static RepositoryHelper dbHelper;
    private static Repository repository;

    private Context context;

    private MutableLiveData<ArrayList<Reserva>> reservaList;
    private MutableLiveData<ArrayList<Plato>> platosList;
    private static SQLiteDatabase database;
    private ArrayList<Reserva> reservaArrayList;
    private ArrayList<Plato> platosArrayList;

    public Repository(Context c) {
        context = c;
        reservaList = new MutableLiveData<>();
        platosList = new MutableLiveData<>();
        reservaArrayList = new ArrayList<>();
        platosArrayList= new ArrayList<>();
    }

    public static Repository open(Context context) throws SQLException {
        if (repository == null) {
            dbHelper = new RepositoryHelper(context);
            database = dbHelper.getWritableDatabase();
            repository = new Repository(context);
        }
        return repository;
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
        reservaList.postValue(reservaArrayList);
        return cursor;
    }

    public ArrayList<Reserva> getReservas() {
        return reservaArrayList;
    }

    public LiveData<ArrayList<Reserva>> getReservaLive() {
        return reservaList;
    }

    public LiveData<ArrayList<Plato>> getPlatosLive() {
        return platosList;
    }

    public void getPlatos() {
        //Lanzar thread de consulta JSON API
        MiHilo thread = new MiHilo();
        thread.execute("https://jdarestaurantapi.firebaseio.com/menu/principales.json");
    }

    public class MiHilo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection;
            URL url;
            connection = null;
            String result;
            result = "";

            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while (data != -1) {
                    result += (char) data;
                    data = inputStream.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.i("RESULT", result);
            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            ArrayList<Plato> platosLista = new ArrayList<>();

            //JSON format
            try {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject;
                    jsonObject = jsonArray.getJSONObject(i);

                    //Traspasar el formato JSON a mi model
                    Plato plato = new Plato(jsonObject);
                    platosLista.add(plato);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            platosList.postValue(platosLista);

        }
    }
}

