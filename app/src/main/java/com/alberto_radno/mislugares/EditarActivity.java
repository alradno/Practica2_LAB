package com.alberto_radno.mislugares;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.List;

public class EditarActivity extends AppCompatActivity {

    ImageView foto;
    EditText nombreEditado;
    EditText localizacionEditada;
    EditText tipoEditado;
    RatingBar valoracionEditada;
    EditText telefonoEditado;
    EditText urlEditada;
    EditText comentarioEditado;
    int id;
    Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Toast.makeText(this, "Modo edicion", Toast.LENGTH_SHORT).show();

        foto = findViewById(R.id.foto_edit);
        nombreEditado = findViewById(R.id.nombre_edit);
        localizacionEditada = findViewById(R.id.localizacion_edit);
        tipoEditado = findViewById(R.id.tipo_edit);
        valoracionEditada = findViewById(R.id.valoracion_edit);
        telefonoEditado = findViewById(R.id.telefono_edit);
        urlEditada = findViewById(R.id.url_edit);
        comentarioEditado = findViewById(R.id.comentario_edit);

        id = getIntent().getIntExtra("id", 0);

        AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "lugares").allowMainThreadQueries().build();
        System.out.println("ID: " + id);


        lugar = db.lugarDao().findById(id);

        //foto.setImageResource(lugar.getFoto());
        nombreEditado.setText(lugar.getNombre());
        localizacionEditada.setText(lugar.getLocalizacion());
        tipoEditado.setText(lugar.getTipo());
        valoracionEditada.setRating(lugar.getValoracion());

        if(lugar.getTelefono() != null){
            telefonoEditado.setText(lugar.getTelefono());
        }

        if(lugar.getUrl() != null){
            urlEditada.setText(lugar.getUrl());
        }

        if(lugar.getComentario() != null){
            comentarioEditado.setText(lugar.getComentario());
        }

        /*if(lugar.getFoto() != null){
            foto.setImageResource(lugar.getFoto());
        }*/

    }
}