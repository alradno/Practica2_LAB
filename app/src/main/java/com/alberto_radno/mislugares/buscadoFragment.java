package com.alberto_radno.mislugares;

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

public class buscadoFragment extends Fragment {

    String nombreBuscado;
    String localizacionBuscada;
    float valoracionBuscada;
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
                buscar();
                buscar2();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscado, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //resultados = view.findViewById(R.id.resultadosText);

    }

    public void mostrarResultados(String resultados) {
        this.resultados.setText(resultados);
    }

    public void buscar(){

        //Buscar en la base de datos
        AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();

        if(nombreBuscado != null || localizacionBuscada != null || valoracionBuscada != 0) {

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
            //Buscar por localizacion y valoracion
            else if(localizacionBuscada != null && valoracionBuscada != 0){
                //Log.i("__localizacionBuscada", localizacionBuscada);
                //Log.i("__valoracionBuscada", String.valueOf(valoracionBuscada));
                lugares = db.lugarDao().findAllByLocationAndRating(localizacionBuscada, valoracionBuscada);
            }
            //Si existe al menos un lugar con los datos buscados
            if(lugares != null){
                //Mostrar todos los lugares encontrados en el recycler view
                recyclerView = getView().findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new AdapterLugar(getContext(), lugares, R.layout.item_lugar));
            }
            else{
                //resultados.append("No se ha encontrado ningún lugar con esos datos");
                Toast.makeText(getContext(), "No se ha encontrado ningún lugar con esos datos", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getContext(), "No se ha introducido ningún criterio de búsqueda", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar2(){

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
    }
}