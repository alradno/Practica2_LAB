package com.alberto_radno.mislugares;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class infoActivity extends AppCompatActivity {

    ImageView foto;
    TextView nombre;
    TextView localizacion;
    RatingBar valoracion;
    TextView tipo;
    TextView telefono;
    TextView url;
    TextView comentario;
    Lugar lugar;
    elegir_Foto elegirFoto;

    Button aceptar;
    Button eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        int id = getIntent().getIntExtra("id", 0);
        AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "lugares").allowMainThreadQueries().build();
        lugar = db.lugarDao().findById(id);

        nombre = findViewById(R.id.nombre_editado);
        localizacion = findViewById(R.id.localizacion_editado);
        valoracion = findViewById(R.id.ratingBar_editado);
        tipo = findViewById(R.id.tipo_editado);
        telefono = findViewById(R.id.telefono_editado);
        url = findViewById(R.id.url_editado);
        comentario = findViewById(R.id.comentario_editado);
        foto = findViewById(R.id.foto_editado);

        aceptar = findViewById(R.id.aceptarButton_editado);
        eliminar = findViewById(R.id.eliminarButton);

        nombre.setText(lugar.getNombre());
        localizacion.setText(lugar.getLocalizacion());
        valoracion.setRating(lugar.getValoracion());
        tipo.setText(lugar.getTipo());
        telefono.setText(lugar.getTelefono());
        url.setText(lugar.getUrl());
        comentario.setText(lugar.getComentario());

        elegirFoto = new elegir_Foto();
        int idFoto = elegirFoto.elegirFoto(lugar.getTipo());
        foto.setImageResource(idFoto);


        //Click en boton aceptar y volver a main
        aceptar.setOnClickListener(v ->
                finish());


        eliminar.setOnClickListener(v -> {
            db.lugarDao().delete(lugar);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new buscadoFragment()).commit();
        });
    }

}