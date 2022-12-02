package com.alberto_radno.mislugares;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;

public class EditarActivity extends AppCompatActivity {

    ImageView foto;
    EditText nombreEditado;
    EditText localizacionEditada;
    EditText tipoEditado;
    RatingBar valoracionEditada;
    EditText telefonoEditado;
    EditText urlEditada;
    EditText comentarioEditado;
    EditText urlEditadaFoto;
    Button aceptar;
    Button eliminar;
    int id;
    Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Toast.makeText(this, "Modo edicion", Toast.LENGTH_SHORT).show();

        foto = findViewById(R.id.foto_editado);
        nombreEditado = findViewById(R.id.nombre_editado);
        localizacionEditada = findViewById(R.id.localizacion_editado);
        tipoEditado = findViewById(R.id.tipo_editado);
        valoracionEditada = findViewById(R.id.ratingBar_editado);
        telefonoEditado = findViewById(R.id.telefono_editado);
        urlEditada = findViewById(R.id.url_editado);
        comentarioEditado = findViewById(R.id.comentario_editado);
        urlEditadaFoto = findViewById(R.id.url_foto_editado);
        aceptar = findViewById(R.id.aceptarButton_editado);
        eliminar = findViewById(R.id.eliminarButton);

        id = getIntent().getIntExtra("id", 0);

        AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "lugares").allowMainThreadQueries().build();
        System.out.println("ID: " + id);


        lugar = db.lugarDao().findById(id);


        nombreEditado.setText(lugar.getNombre());
        localizacionEditada.setText(lugar.getLocalizacion());
        tipoEditado.setText(lugar.getTipo());
        valoracionEditada.setRating(lugar.getValoracion());
        telefonoEditado.setText(lugar.getTelefono());
        urlEditada.setText(lugar.getUrl());
        comentarioEditado.setText(lugar.getComentario());

        //Colocar foto
        elegir_Foto elegirfoto = new elegir_Foto();
        elegirfoto.elegirFoto(lugar.getTipo());
        id = elegirfoto.elegirFoto(lugar.getTipo());
        foto.setImageResource(id);


        /*if(lugar.getFoto() != null){
            urlEditadaFoto.setText(lugar.getFoto());
            Glide.with(this).load(lugar.getFoto()).into(foto);
        }*/

        aceptar.setOnClickListener(v -> {
            lugar.setNombre(nombreEditado.getText().toString());
            lugar.setLocalizacion(localizacionEditada.getText().toString());
            lugar.setTipo(tipoEditado.getText().toString());
            lugar.setValoracion(valoracionEditada.getRating());
            lugar.setTelefono(telefonoEditado.getText().toString());
            lugar.setUrl(urlEditada.getText().toString());
            lugar.setComentario(comentarioEditado.getText().toString());
            //lugar.setFoto(urlEditadaFoto.getText().toString());
            db.lugarDao().update(lugar);
            Toast.makeText(this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        });

        eliminar.setOnClickListener(v -> {
            db.lugarDao().delete(lugar);
            finish();
        });

        /*//Si hace click en foto_info, se abre el selector de fotos y selecciona una foto de la galeria
        foto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 10);
            //Asigna la foto seleccionada a la ImageView foto y la guarda en la base de datos
            foto.setImageURI(intent.getData());
            lugar.setFoto(intent.getData().toString());
        });*/



    }
}