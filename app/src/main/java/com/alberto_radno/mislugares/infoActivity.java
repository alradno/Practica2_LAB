package com.alberto_radno.mislugares;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class infoActivity extends AppCompatActivity {

    TextView nombre;
    TextView localizacion;
    RatingBar valoracion;
    TextView tipo;
    TextView telefono;
    TextView url;
    TextView comentario;
    Lugar lugar;
    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        int id = getIntent().getIntExtra("id", 0);
        AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "lugares").allowMainThreadQueries().build();
        lugar = db.lugarDao().findById(id);

        nombre = findViewById(R.id.nombre_info);
        localizacion = findViewById(R.id.localizacion_info);
        valoracion = findViewById(R.id.ratingBar_info);
        tipo = findViewById(R.id.tipo_info);
        telefono = findViewById(R.id.telefono_info);
        url = findViewById(R.id.url_info);
        comentario = findViewById(R.id.comentario_info);
        aceptar = findViewById(R.id.aceptarButton_info);

        nombre.setText(lugar.getNombre());
        localizacion.setText(lugar.getLocalizacion());
        valoracion.setRating(lugar.getValoracion());
        tipo.setText(lugar.getTipo());
        telefono.setText(lugar.getTelefono());
        url.setText(lugar.getUrl());
        comentario.setText(lugar.getComentario());

        //Click en boton aceptar y volver a main
        aceptar.setOnClickListener(v ->
                finish());
        }


}