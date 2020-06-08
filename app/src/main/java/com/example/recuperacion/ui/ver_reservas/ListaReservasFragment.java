package com.example.recuperacion.ui.ver_reservas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recuperacion.Model.Reserva;
import com.example.recuperacion.R;

import java.util.ArrayList;
import java.util.List;

public class ListaReservasFragment extends Fragment {

    private VerReservasViewModel verReservasViewModel;
    RecyclerView reservas_recycler;
    List<Reserva> reservas = new ArrayList<>();
    ReservaAdapter reservaAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        verReservasViewModel =
                ViewModelProviders.of(this).get(VerReservasViewModel.class);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vista_reserva, container, false);


        reservas_recycler = view.findViewById(R.id.reservas_Recycler);
        reservas_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        reservaAdapter = new ReservaAdapter(reservas);
        reservas_recycler.setAdapter(reservaAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        reservas_recycler.addItemDecoration(dividerItemDecoration);

        return view;
    }


    public class ReservaViewHolder extends RecyclerView.ViewHolder{

        TextView fecha, personas;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);

            fecha = itemView.findViewById(R.id.fechaViewHolder);
            personas = itemView.findViewById(R.id.personasViewHolder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    public class ReservaAdapter extends RecyclerView.Adapter<ReservaViewHolder> {

        List<Reserva> reservaList;

        public ReservaAdapter(List<Reserva> reservaList) {
            this.reservaList = reservaList;
        }

        @NonNull
        @Override
        public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View itemview = getLayoutInflater().inflate(R.layout.reserva_viewholder, viewGroup, false);
            return new ReservaViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull ReservaViewHolder reservaViewHolder, int i) {

            reservaViewHolder.fecha.setText("0");
            reservaViewHolder.personas.setText("0");

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
