package com.alberto_radno.mislugares;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class EditarActivity extends AppCompatActivity {

    ImageView foto;
    EditText nombreEditado;
    EditText localizacionEditada;
    EditText tipoEditado;
    RatingBar valoracionEditada;
    EditText telefonoEditado;
    EditText urlEditada;
    EditText comentarioEditado;
    Button aceptar;
    int id;
    Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Toast.makeText(this, "Modo edicion", Toast.LENGTH_SHORT).show();

        foto = findViewById(R.id.foto_info);
        nombreEditado = findViewById(R.id.nombre_info);
        localizacionEditada = findViewById(R.id.localizacion_info);
        tipoEditado = findViewById(R.id.tipo_info);
        valoracionEditada = findViewById(R.id.ratingBar_info);
        telefonoEditado = findViewById(R.id.telefono_info);
        urlEditada = findViewById(R.id.url_info);
        comentarioEditado = findViewById(R.id.comentario_info);
        aceptar = findViewById(R.id.aceptarButton_info);

        id = getIntent().getIntExtra("id", 0);

        AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "lugares").allowMainThreadQueries().build();
        System.out.println("ID: " + id);


        lugar = db.lugarDao().findById(id);

        //foto.setImageResource(lugar.getFoto());
        nombreEditado.setText(lugar.getNombre());
        localizacionEditada.setText(lugar.getLocalizacion());
        tipoEditado.setText(lugar.getTipo());
        valoracionEditada.setRating(lugar.getValoracion());
        telefonoEditado.setText(lugar.getTelefono());
        urlEditada.setText(lugar.getUrl());
        comentarioEditado.setText(lugar.getComentario());

        aceptar.setOnClickListener(v -> {
            lugar.setNombre(nombreEditado.getText().toString());
            lugar.setLocalizacion(localizacionEditada.getText().toString());
            lugar.setTipo(tipoEditado.getText().toString());
            lugar.setValoracion(valoracionEditada.getRating());
            lugar.setTelefono(telefonoEditado.getText().toString());
            lugar.setUrl(urlEditada.getText().toString());
            lugar.setComentario(comentarioEditado.getText().toString());
            db.lugarDao().update(lugar);
            Toast.makeText(this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        });


    }
}