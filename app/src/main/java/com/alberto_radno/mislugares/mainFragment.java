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
    Button editar;
    Button borrar;

    EditText nombreText;
    EditText localizacionText;
    RatingBar ratingBar;
    EditText tipoText;


    public mainFragment() {
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
        } else {
            Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

        nombreText.setText("");
        localizacionText.setText("");
        ratingBar.setRating(0);
        tipoText.setText("");

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

}