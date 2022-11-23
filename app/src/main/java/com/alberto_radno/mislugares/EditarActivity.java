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

        foto = findViewById(R.id.foto_edit);
        nombreEditado = findViewById(R.id.nombre_edit);
        localizacionEditada = findViewById(R.id.localizacion_edit);
        tipoEditado = findViewById(R.id.tipo_edit);
        valoracionEditada = findViewById(R.id.ratingBar_edit);
        telefonoEditado = findViewById(R.id.telefono_edit);
        urlEditada = findViewById(R.id.url_edit);
        comentarioEditado = findViewById(R.id.comentario_edit);
        aceptar = findViewById(R.id.aceptarButton_edit);

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