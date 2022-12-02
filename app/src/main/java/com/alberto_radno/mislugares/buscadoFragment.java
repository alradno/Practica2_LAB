package com.alberto_radno.mislugares;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class buscadoFragment extends Fragment  implements RecyclerViewInterface{

    String nombreBuscado;
    String localizacionBuscada;
    float valoracionBuscada;
    String tipoBuscado;
    List <Lugar> lugares;

    RecyclerView recyclerView;

    public buscadoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recogemos los datos del fragment anterior
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nombreBuscado = bundle.getString("nombreBuscado");
            localizacionBuscada = bundle.getString("localizacionBuscada");
            valoracionBuscada = bundle.getFloat("valoracionBuscada");
            tipoBuscado = bundle.getString("tipoBuscado");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscado, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buscar(nombreBuscado, localizacionBuscada, valoracionBuscada, tipoBuscado);

    }

    public void buscar(String nombre, String localizacion, float valoracion, String tipo){

        //Buscar en la base de datos
        AppDataBase db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        //db.lugarDao().deleteAll();

        //Si hay algun filtro de busqueda
        if(!nombre.equals("") || !localizacion.equals("") || valoracion != 0 || !tipo.equals("")) {

            //Buscar por nombre
            if (!nombre.equals("")) {
                //Log.i("__nombreBuscado", nombreBuscado);
                lugares = db.lugarDao().findAllByName(nombre);
            }
            //Buscar por localizacion
            else if (!localizacion.equals("") && valoracion == 0) {
                //Log.i("__localizacionBuscada", localizacionBuscada);
                lugares = db.lugarDao().findAllByLocation(localizacion);

            }
            //Buscar por valoracion
            else if (valoracion != 0 && localizacion.equals("")) {
                //Log.i("__valoracionBuscada", String.valueOf(valoracionBuscada));
                lugares = db.lugarDao().findAllByRating(valoracion);
            }
            //Buscar por tipo
            else if(!tipo.equals("")){
                //Log.i("__tipoBuscado", tipoBuscado);
                lugares = db.lugarDao().findAllByType(tipo);
            }
            //Buscar por localizacion y valoracion
            else if(!localizacion.equals("") && valoracion != 0){
                //Log.i("__localizacionBuscada", localizacionBuscada);
                lugares = db.lugarDao().findAllByLocationAndRating(localizacion, valoracion);
            }
            //Si existe al menos un lugar con los datos buscados
            if(lugares.size() > 0){
                //Mostrar todos los lugares encontrados en el recycler view
                recyclerView = requireView().findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new AdapterLugar(getContext(), lugares, this));
                System.out.println("Lugares encontrados: " + lugares.size());
            }
            else{
                Toast.makeText(getContext(), "No se ha encontrado ningún lugar con esos datos", Toast.LENGTH_SHORT).show();
            }
        }
        //Si no hay filtros de busqueda se muestran todos los lugares
        else{
            //Borar todos los lugares de la base de datos
            //db.lugarDao().deleteAll();
            lugares = db.lugarDao().findAll();

            if(lugares.size() > 0){
                //Mostrar todos los lugares encontrados en el recycler view
                recyclerView = requireView().findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new AdapterLugar(getContext(), lugares, this));
                Toast.makeText(getContext(), "Mostrando todos los lugares", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), "La base de datos está vacía", Toast.LENGTH_SHORT).show();
            }
        }
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

    /*@Override
    public void onFavoritoClick(int position) {
        //Cambiar el estado del favorito
        AppDataBase db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        Lugar lugar = lugares.get(position);
        if(lugar.getFavorito() == false){
            lugar.setFavorito(true);
            Toast.makeText(getContext(), "Añadido a favoritos", Toast.LENGTH_SHORT).show();
        }
        else{
            lugar.setFavorito(false);
            Toast.makeText(getContext(), "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
        }
        db.lugarDao().update(lugar);
    }*/
}