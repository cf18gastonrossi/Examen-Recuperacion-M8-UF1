package com.example.recuperacion.ui.detall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recuperacion.Model.Reserva;
import com.example.recuperacion.R;
import com.example.recuperacion.repository.Repository;

import java.util.ArrayList;

public class DetallFragment extends Fragment {

    private Repository repository;

    private TextView fecha, comensales, nombre, telefono, comentarios;
    private ArrayList<Reserva> reservas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detalle_reserva, container, false);

        fecha = root.findViewById(R.id.fechaTextView);
        comensales = root.findViewById(R.id.comensalesTextView);
        nombre = root.findViewById(R.id.nombreTextView);
        telefono = root.findViewById(R.id.telefonoTextView);
        comentarios = root.findViewById(R.id.comentariosTextView);

        repository = Repository.open(getContext());
        updateReserva(repository.getReservas().get(getArguments().getInt("POSITION")));

        return root;
    }

    private void updateReserva(Reserva reserva) {

        fecha.setText(reserva.getFecha());
        comensales.setText(String.valueOf(reserva.getPersonas()));
        nombre.setText(reserva.getNombre());
        telefono.setText(reserva.getTelefono());
        comentarios.setText(reserva.getComentarios());
    }
}
