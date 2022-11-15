package com.alberto_radno.mislugares;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class buscadoFragment extends Fragment {

    String nombreBuscado;
    String localizacionBuscada;
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

                nombreBuscado = (String)bundle.getString("nombreBuscado");
                localizacionBuscada = (String)bundle.getString("localizacionBuscada");

                Toast.makeText(getContext(), "Buscando por "+nombreBuscado, Toast.LENGTH_SHORT).show();
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


        //Buscar en la base de datos
        miBD admin = new miBD(getContext(), "lugares", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        resultados = view.findViewById(R.id.resultadosText);

        if(nombreBuscado != null){
            Cursor fila = BD.rawQuery("SELECT * FROM lugares WHERE nombre = " + nombreBuscado, null);
            while(fila.moveToNext()){
                resultados.append(fila.getString(0) + " " + fila.getString(1) + " " + fila.getString(2) + " " + fila.getString(3));
            }
        }
        else if(localizacionBuscada != null){
            Cursor fila = BD.rawQuery("SELECT * FROM lugares WHERE localizacion = " + localizacionBuscada, null);
            while(fila.moveToNext()){
                resultados.append(fila.getString(0) + " " + fila.getString(1) + " " + fila.getString(2) + " " + fila.getString(3));
            }
        }
        else{
            resultados.setText("No se ha encontrado nada");
        }
    }

    public void mostrarResultados(String resultados) {
        this.resultados.setText(resultados);
    }
}