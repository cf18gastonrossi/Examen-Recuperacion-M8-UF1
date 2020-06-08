package com.example.recuperacion.ui.carta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recuperacion.Model.Plato;
import com.example.recuperacion.R;

import java.util.ArrayList;
import java.util.List;

public class CartaFragment extends Fragment {

    private CartaViewModel cartaViewModel;
    RecyclerView platosRecycler;
    List<Plato>  platoList = new ArrayList<>();
    PlatosAdapter platosAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartaViewModel =
                ViewModelProviders.of(this).get(CartaViewModel.class);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_platos_menu, container, false);

        platosRecycler =  view.findViewById(R.id.platosRecycler);
        platosRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        platosRecycler.addItemDecoration(divider);
        platosAdapter = new PlatosAdapter(platoList);
        platosRecycler.setAdapter(platosAdapter);



        return view;
    }


    public class PlatoViewHolder extends RecyclerView.ViewHolder{

        TextView plato,ingredientes,precio;

        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);

            plato = itemView.findViewById(R.id.platoTextView);
            ingredientes = itemView.findViewById(R.id.ingredientesTextView);
            precio = itemView.findViewById(R.id.precioTextView);
        }
    }

    public class PlatosAdapter extends RecyclerView.Adapter<PlatoViewHolder>{

        List<Plato> platos;

        public PlatosAdapter(List<Plato> platos) {
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

            platoViewHolder.precio.setText("0");
            platoViewHolder.ingredientes.setText("0");
            platoViewHolder.plato.setText("0");

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
