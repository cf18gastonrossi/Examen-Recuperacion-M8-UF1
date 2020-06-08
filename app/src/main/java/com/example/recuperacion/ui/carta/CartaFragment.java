package com.example.recuperacion.ui.carta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recuperacion.Model.Plato;
import com.example.recuperacion.Model.Reserva;
import com.example.recuperacion.R;
import com.example.recuperacion.repository.Repository;
import com.example.recuperacion.ui.ver_reservas.ListaReservasFragment;

import java.util.ArrayList;
import java.util.List;

public class CartaFragment extends Fragment {

    private CartaViewModel cartaViewModel;
    RecyclerView platosRecycler;
    ArrayList<Plato> platoList = new ArrayList<>();
    PlatosAdapter platosAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartaViewModel =
                ViewModelProviders.of(this).get(CartaViewModel.class);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_platos_menu, container, false);

        Repository repository = Repository.open(getContext());
        repository.getPlatos();
        repository.getPlatosLive().observe(getViewLifecycleOwner(), new Observer<ArrayList<Plato>>() {
            @Override
            public void onChanged(ArrayList<Plato> platosList) {
                platoList = platosList;
                platosAdapter = new PlatosAdapter(platosList);
                platosRecycler.setAdapter(platosAdapter);
            }
        });
        platosRecycler = view.findViewById(R.id.platosRecycler);
        platosRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        platosRecycler.addItemDecoration(divider);
        platosAdapter = new PlatosAdapter(platoList);
        platosRecycler.setAdapter(platosAdapter);


        return view;
    }


    public class PlatoViewHolder extends RecyclerView.ViewHolder {

        TextView plato, ingredientes, precio;

        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);

            plato = itemView.findViewById(R.id.platoTextView);
            ingredientes = itemView.findViewById(R.id.ingredientesTextView);
            precio = itemView.findViewById(R.id.precioTextView);
        }
    }

    public class PlatosAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

        ArrayList<Plato> platos;

        public PlatosAdapter(ArrayList<Plato> platos) {
            this.platos = platos;
        }

        @NonNull
        @Override
        public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View itemview = getLayoutInflater().inflate(R.layout.plato_viewholder, viewGroup, false);
            return new PlatoViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull PlatoViewHolder platoViewHolder, int i) {

            Integer precio = platos.get(i).getPrecio();
            String ingredientes = platos.get(i).getIngredientes();
            String plato = platos.get(i).getNombre_plato();

            platoViewHolder.precio.setText(String.valueOf(precio));
            platoViewHolder.ingredientes.setText(ingredientes);
            platoViewHolder.plato.setText(plato);

        }

        @Override
        public int getItemCount() {
            return platos.size();
        }
    }

}
