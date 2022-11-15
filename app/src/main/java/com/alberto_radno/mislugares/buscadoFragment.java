package com.alberto_radno.mislugares;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class buscadoFragment extends Fragment {

    String nombreBuscado;
    String localizacionBuscada;
    float valoracionBuscada;
    EditText resultados;

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

                if(nombreBuscado != null){
                    Log.i("__nombreBuscado", nombreBuscado);
                }
                nombreBuscado = (String)bundle.getString("nombreBuscado");
                localizacionBuscada = (String)bundle.getString("localizacionBuscada");
                if(localizacionBuscada != null){
                    Log.i("__Localizacion2", localizacionBuscada);
                }
                valoracionBuscada = (float)bundle.getFloat("valoracionBuscada");
                if(valoracionBuscada != 0){
                    Log.i("__Valoracion2", String.valueOf(valoracionBuscada));
                }
                buscar();
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
        resultados = view.findViewById(R.id.resultadosText);

    }

    public void mostrarResultados(String resultados) {
        this.resultados.setText(resultados);
    }

    public void buscar(){

        //Buscar en la base de datos
        AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();

        if(nombreBuscado != null || localizacionBuscada != null || valoracionBuscada != 0) {

            Lugar lugar = null;

            if (nombreBuscado != null) {
                //Log.i("__nombreBuscado", nombreBuscado);
                lugar = db.lugarDao().findByName(nombreBuscado);
            } else if (localizacionBuscada != null) {
                //Log.i("__localizacionBuscada", localizacionBuscada);
                lugar = db.lugarDao().findByLocation(localizacionBuscada);

            } else if (valoracionBuscada != 0) {
                //Log.i("__valoracionBuscada", String.valueOf(valoracionBuscada));
                lugar = db.lugarDao().findByRating(valoracionBuscada);
            }
            if(lugar != null){
                resultados.append(lugar.getNombre() + " " + lugar.getLocalizacion() + " " + lugar.getValoracion() + "\n");
            }else{
                resultados.append("No se ha encontrado ningún lugar con esos datos");
            }
        }
        else{
            Toast.makeText(getContext(), "No se ha introducido ningún criterio de búsqueda", Toast.LENGTH_SHORT).show();
        }
    }
}