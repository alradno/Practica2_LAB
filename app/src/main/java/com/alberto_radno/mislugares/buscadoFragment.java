package com.alberto_radno.mislugares;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class buscadoFragment extends Fragment  implements RecyclerViewInterface{

    String nombreBuscado;
    String localizacionBuscada;
    float valoracionBuscada;
    String tipoBuscado;
    EditText resultados;
    List<Lugar> lugares;
    RecyclerView recyclerView;

    public buscadoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Obtener datos del bundle de mainFragment
        getParentFragmentManager().setFragmentResultListener("Main_a_Buscado", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {

                nombreBuscado = (String)bundle.getString("nombreBuscado");
                if(nombreBuscado != null){
                    Log.i("__nombreBuscado", nombreBuscado);
                }
                localizacionBuscada = (String)bundle.getString("localizacionBuscada");
                if(localizacionBuscada != null){
                    Log.i("__Localizacion2", localizacionBuscada);
                }
                valoracionBuscada = (float)bundle.getFloat("valoracionBuscada");
                if(valoracionBuscada != 0){
                    Log.i("__Valoracion2", String.valueOf(valoracionBuscada));
                }
                tipoBuscado = (String)bundle.getString("tipoBuscado");
                if(tipoBuscado != null){
                    Log.i("__Tipo2", tipoBuscado);
                }
                System.out.println("****************************************************************");
                Log.i("__********************", "****************");
                buscar();
                //buscar2();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscado, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //resultados = view.findViewById(R.id.resultadosText);

    }

    public void mostrarResultados(String resultados) {
        this.resultados.setText(resultados);
    }

    public void buscar(){

        //Buscar en la base de datos
        AppDataBase db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();
        //db.lugarDao().deleteAll();

        //Si hay algun filtro de busqueda
        if(nombreBuscado != null || localizacionBuscada != null || valoracionBuscada != 0 || tipoBuscado != null) {

            //Buscar por nombre
            if (nombreBuscado != null) {
                //Log.i("__nombreBuscado", nombreBuscado);
                lugares = db.lugarDao().findAllByName(nombreBuscado);
            }
            //Buscar por localizacion
            else if (localizacionBuscada != null && valoracionBuscada == 0) {
                //Log.i("__localizacionBuscada", localizacionBuscada);
                lugares = db.lugarDao().findAllByLocation(localizacionBuscada);

            }
            //Buscar por valoracion
            else if (valoracionBuscada != 0 && localizacionBuscada == null) {
                //Log.i("__valoracionBuscada", String.valueOf(valoracionBuscada));
                lugares = db.lugarDao().findAllByRating(valoracionBuscada);
            }
            //Buscar por tipo
            else if(tipoBuscado != null){
                //Log.i("__tipoBuscado", tipoBuscado);
                lugares = db.lugarDao().findAllByType(tipoBuscado);
            }
            //Buscar por localizacion y valoracion
            else if(localizacionBuscada != null && valoracionBuscada != 0){
                //Log.i("__localizacionBuscada", localizacionBuscada);
                //Log.i("__valoracionBuscada", String.valueOf(valoracionBuscada));
                lugares = db.lugarDao().findAllByLocationAndRating(localizacionBuscada, valoracionBuscada);
            }
            //Si existe al menos un lugar con los datos buscados
            if(lugares.size() > 0){
                //Mostrar todos los lugares encontrados en el recycler view
                recyclerView = getView().findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new AdapterLugar(getContext(), lugares, this));
                System.out.println("Lugares encontrados: " + lugares.size());
            }
            else{
                Toast.makeText(getContext(), "No se ha encontrado ningún lugar con esos datos", Toast.LENGTH_SHORT).show();
            }
        }
        //Si no hay filtros de busqueda
        else{

            /*lugares = db.lugarDao().findAll();
            System.out.println("*************************************************************************************************************************************************************");

            if(lugares.size() > 0){
                //Mostrar todos los lugares encontrados en el recycler view
                recyclerView = getView().findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new AdapterLugar(getContext(), lugares, this));
                Toast.makeText(getContext(), "Mostrando todos los lugares", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), "La base de datos está vacía", Toast.LENGTH_SHORT).show();
            }*/
            Toast.makeText(getContext(), "No se ha introducido ningún filtro de búsqueda", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Click en " + lugares.get(position).getNombre(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClick(int position) {
        //Toast.makeText(getContext(), "Long click en " + lugares.get(position).getNombre(), Toast.LENGTH_SHORT).show();
        //Pasar el lugar selleccionado a la actvity de editar
        Intent intent = new Intent(getActivity(), EditarActivity.class);
        intent.putExtra("id", lugares.get(position).getId());
        startActivity(intent);

    }

    /*public void buscar2(){

        //Buscar en la base de datos
        AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();

        if(nombreBuscado != null || localizacionBuscada != null || valoracionBuscada != 0) {

            List<Lugar> lugar = null;

            //Buscar por nombre
            if (nombreBuscado != null) {
                //Log.i("__nombreBuscado", nombreBuscado);
                lugar = db.lugarDao().findAllByName(nombreBuscado);
            }
            //Buscar por localizacion
            else if (localizacionBuscada != null && valoracionBuscada == 0) {
                //Log.i("__localizacionBuscada", localizacionBuscada);
                lugar = db.lugarDao().findAllByLocation(localizacionBuscada);

            }
            //Buscar por valoracion
            else if (valoracionBuscada != 0 && localizacionBuscada == null) {
                //Log.i("__valoracionBuscada", String.valueOf(valoracionBuscada));
                lugar = db.lugarDao().findAllByRating(valoracionBuscada);
            }
            //Buscar por localizacion y valoracion
            else if(localizacionBuscada != null && valoracionBuscada != 0){
                //Log.i("__localizacionBuscada", localizacionBuscada);
                //Log.i("__valoracionBuscada", String.valueOf(valoracionBuscada));
                lugar = db.lugarDao().findAllByLocationAndRating(localizacionBuscada, valoracionBuscada);
            }
            //Si existe al menos un lugar con los datos buscados
            if(lugar != null){
                //Mostrar todos los lugares encontrados
                for(Lugar l : lugar){
                    //resultados.append(l.getNombre() + " " + l.getLocalizacion() + " " + l.getValoracion() + "\n");
                    System.out.println(l.getNombre() + " " + l.getLocalizacion() + " " + l.getValoracion());
                }
            }
            else{
                //resultados.append("No se ha encontrado ningún lugar con esos datos");
                Toast.makeText(getContext(), "No se ha encontrado ningún lugar con esos datos", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getContext(), "No se ha introducido ningún criterio de búsqueda", Toast.LENGTH_SHORT).show();
        }
    }*/
}