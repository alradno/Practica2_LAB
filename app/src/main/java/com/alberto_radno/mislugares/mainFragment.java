package com.alberto_radno.mislugares;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class mainFragment extends Fragment {

    Button anadir;
    Button buscar;

    EditText nombreText;
    EditText localizacionText;
    RatingBar ratingBar;
    EditText tipoText;


    String nombreBuscado;
    String localizacionBuscada;
    float valoracionBuscada;
    String tipoBuscado;


    public mainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        anadir = view.findViewById(R.id.anadirButton);
        buscar = view.findViewById(R.id.buscarButton);

        nombreText = view.findViewById(R.id.nombreText);
        localizacionText = view.findViewById(R.id.localizacionText);
        ratingBar = view.findViewById(R.id.ratingBar_item);
        tipoText = view.findViewById(R.id.tipoText);

        nombreText.setText(nombreBuscado);
        localizacionText.setText(localizacionBuscada);
        ratingBar.setRating(valoracionBuscada);
        tipoText.setText(tipoBuscado);

        anadir.setOnClickListener( v -> {
            {
                anadir();
            }
        });

        buscar.setOnClickListener( v -> {
            {
                buscar();
            }
        });

        tipoText.setOnClickListener( v -> {
            {
                tipo();
            }
        });

    }

    public void anadir() {
        //Obtener datos de los campos
        String nombre = nombreText.getText().toString();
        String localizacion = localizacionText.getText().toString();
        float valoracion = ratingBar.getRating();
        String tipo = tipoText.getText().toString();

        if(!nombre.isEmpty() && !localizacion.isEmpty() && valoracion != 0 && !tipo.isEmpty()){
            //Crear objeto Lugar
            Lugar lugar = new Lugar(nombre, localizacion, valoracion, tipo);
            //Insertar en la base de datos
            AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();
            db.lugarDao().insert(lugar);
            Toast.makeText(getContext(), "Lugar añadido", Toast.LENGTH_SHORT).show();

            nombreText.setText("");
            localizacionText.setText("");
            ratingBar.setRating(0);
            tipoText.setText("");

        } else {
            Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscar(){

        String nombre = nombreText.getText().toString();
        String localizacion = localizacionText.getText().toString();
        float valoracion = ratingBar.getRating();
        String tipo = tipoText.getText().toString();

        Bundle result = new Bundle();
        Fragment fragment = new buscadoFragment();

        result.putString("nombreBuscado", nombre);
        result.putString("localizacionBuscada", localizacion);
        result.putFloat("valoracionBuscada", valoracion);
        result.putString("tipoBuscado", tipo);

        fragment.setArguments(result);

        /*NavController navController = Navigation.findNavController(this.requireView());
        navController.navigate(R.id.action_mainFragment_to_buscadoFragment, result);*/

        getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();

    }

    public void tipo(){
        String nombre = nombreText.getText().toString();
        String localizacion = localizacionText.getText().toString();
        float valoracion = ratingBar.getRating();
        String tipo = tipoText.getText().toString();

        Bundle result = new Bundle();
        Fragment fragment = new TipoFragment();

        result.putString("nombreBuscado", nombre);
        result.putString("localizacionBuscada", localizacion);
        result.putFloat("valoracionBuscada", valoracion);
        result.putString("tipoBuscado", tipo);

        fragment.setArguments(result);

        /*NavController navController = Navigation.findNavController(this.requireView());
        navController.navigate(R.id.action_mainFragment_to_buscadoFragment, result);*/

        getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
    }

}