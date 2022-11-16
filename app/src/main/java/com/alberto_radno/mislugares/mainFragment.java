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
    Button mas;

    EditText nombreText;
    EditText localizacionText;
    RatingBar ratingBar;


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
        editar = view.findViewById(R.id.editarButton);
        borrar = view.findViewById(R.id.borrarButton);
        mas = view.findViewById(R.id.masButton);

        nombreText = view.findViewById(R.id.nombreText);
        localizacionText = view.findViewById(R.id.localizacionText);
        ratingBar = view.findViewById(R.id.ratingBar_item);

        anadir.setOnClickListener( v -> {
            {
                anadir();
            }
        });

        buscar.setOnClickListener( v -> {
            {
                buscar(view);
            }
        });

    }

    public void anadir() {
        //Obtener datos de los campos
        String nombre = nombreText.getText().toString();
        String localizacion = localizacionText.getText().toString();
        float valoracion = ratingBar.getRating();

        if(!nombre.isEmpty() && !localizacion.isEmpty() && valoracion != 0) {
            //Crear objeto Lugar
            Lugar lugar = new Lugar(nombre, localizacion, valoracion);
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

    }

    public void buscar(View view){

        String nombre = nombreText.getText().toString();
        String localizacion = localizacionText.getText().toString();
        float valoracion = ratingBar.getRating();


        if(!nombre.isEmpty() || !localizacion.isEmpty() || valoracion != 0){

                Bundle result = new Bundle();

                if(!nombre.isEmpty()){
                    result.putString("nombreBuscado", nombre);
                }
                else if(!localizacion.isEmpty() && valoracion == 0){
                    result.putString("localizacionBuscada", localizacion);
                }
                else if(valoracion != 0 && localizacion.isEmpty()){
                    result.putFloat("valoracionBuscada", valoracion);
                }
                else if(!localizacion.isEmpty() && valoracion != 0){
                    result.putString("localizacionBuscada", localizacion);
                    result.putFloat("valoracionBuscada", valoracion);
                }

                getParentFragmentManager().setFragmentResult("Main_a_Buscado", result);

                //Abrir buscadoFragment
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_mainFragment_to_buscadoFragment);

        }
        else {
            Toast.makeText(getContext(), "Debes rellenar al menos un campo", Toast.LENGTH_SHORT).show();
        }
    }

}