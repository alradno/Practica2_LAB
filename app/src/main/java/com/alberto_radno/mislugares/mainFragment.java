package com.alberto_radno.mislugares;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
        ratingBar = view.findViewById(R.id.ratingBar);

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

        mas.setOnClickListener( v -> {
            {
                mas();
            }
        });

    }

    public void anadir(){
        //Añadir a la base de datos
        miBD admin = new miBD(getContext(), "lugares", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        String nombre = nombreText.getText().toString();
        String localizacion = localizacionText.getText().toString();
        //float valoracion = ratingBar.getRating();

        if(!nombre.isEmpty() && !localizacion.isEmpty()){

            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre);
            registro.put("localizacion", localizacion);
            //registro.put("valoracion", valoracion);

            BD.insert("lugares", null, registro);
            BD.close();

            nombreText.setText("");
            localizacionText.setText("");
            ratingBar.setRating(0);

            Toast.makeText(getContext(), "Añadido Correctamente", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar(View view){
        //Buscar en la base de datos
        //miBD admin = new miBD(getContext(), "lugares", null, 1);
        //SQLiteDatabase BD = admin.getWritableDatabase();

        String nombre = nombreText.getText().toString();
        String localizacion = localizacionText.getText().toString();
        //float valoracion = ratingBar.getRating();


        if(!nombre.isEmpty() || !localizacion.isEmpty()) {

                Bundle result = new Bundle();

                result.putString("nombreBuscado", nombre);
                result.putString("localizacionBuscada", localizacion);
                //result.putFloat("valoracion", valoracion);
                getParentFragmentManager().setFragmentResult("Main_a_Buscado", result);

                //Abrir buscadoFragment
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_mainFragment_to_buscadoFragment);

        }
        else {
            Toast.makeText(getContext(), "Debes rellenar al menos un campo", Toast.LENGTH_SHORT).show();
        }
    }

    public void mas(){
        //Ver en la base de datos
        miBD admin = new miBD(getContext(), "lugares", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        String nombre = nombreText.getText().toString();

        //float valoracion = ratingBar.getRating();

        if(!nombre.isEmpty()){

            Cursor fila = BD.rawQuery("select localizacion from lugares where nombre = " + nombre, null);

            if(fila.moveToFirst()){
                localizacionText.setText(fila.getString(1));
                BD.close();
            }
            else{
                Toast.makeText(getContext(), "No existe el lugar", Toast.LENGTH_SHORT).show();
                BD.close();
            }
        }
        else{
            Toast.makeText(getContext(), "Debes rellenar el campo nombre", Toast.LENGTH_SHORT).show();
        }

    }
}