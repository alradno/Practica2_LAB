package com.alberto_radno.mislugares;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class favoritosFragment extends Fragment implements RecyclerViewInterface{

    RecyclerView recyclerView;
    List<Lugar> lugares;

    public favoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDataBase db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();
        recyclerView = view.findViewById(R.id.recyclerView);
        lugares = db.lugarDao().findAllFavorites();
        recyclerView.setAdapter(new AdapterLugar(getContext(),lugares, this));

    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getActivity(), infoActivity.class);
        intent.putExtra("id", lugares.get(position).getId());
        startActivity(intent);

    }

    @Override
    public void onLongItemClick(int position) {
        //Pasar el lugar seleccionado a la activity de editar
        Intent intent = new Intent(getActivity(), EditarActivity.class);
        intent.putExtra("id", lugares.get(position).getId());
        startActivity(intent);

    }

    @Override
    public void onFavoritoClick(int position) {

        AppDataBase db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        Lugar lugar = db.lugarDao().findById(lugares.get(position).getId());

        if(!lugar.getFavorito()){
            lugar.setFavorito(true);
            Toast.makeText(getContext(), "Añadido a favoritos", Toast.LENGTH_SHORT).show();
        }
        else{
            lugar.setFavorito(false);
            Toast.makeText(getContext(), "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
        }
        db.lugarDao().update(lugar);
        db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();

        //Actualizamos la lista del recycler view
        recyclerView = requireView().findViewById(R.id.recyclerView);
        lugares = db.lugarDao().findAllFavorites();
        recyclerView.setAdapter(new AdapterLugar(getContext(),lugares, this));
    }
}