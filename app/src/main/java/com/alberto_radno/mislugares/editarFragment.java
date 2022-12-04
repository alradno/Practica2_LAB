package com.alberto_radno.mislugares;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Objects;

public class editarFragment extends Fragment {

    ImageView foto;
    EditText nombreEditado;
    EditText localizacionEditada;
    EditText tipoEditado;
    RatingBar valoracionEditada;
    EditText telefonoEditado;
    EditText urlEditada;
    EditText comentarioEditado;
    Button aceptar;
    Button eliminar;
    int id;
    Lugar lugar;
    String tipo;
    String fragmentOrigen;

    public editarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        assert bundle != null;
        fragmentOrigen = bundle.getString("fragmentOrigen");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(), "Modo edicion", Toast.LENGTH_SHORT).show();

        foto = view.findViewById(R.id.foto_editado);
        nombreEditado = view.findViewById(R.id.nombre_editado);
        localizacionEditada = view.findViewById(R.id.localizacion_editado);
        tipoEditado = view.findViewById(R.id.tipo_editado);
        valoracionEditada = view.findViewById(R.id.ratingBar_editado);
        telefonoEditado = view.findViewById(R.id.telefono_editado);
        urlEditada = view.findViewById(R.id.url_editado);
        comentarioEditado = view.findViewById(R.id.comentario_editado);
        aceptar = view.findViewById(R.id.aceptarButton_editado);
        eliminar = view.findViewById(R.id.eliminarButton);

        assert getArguments() != null;
        id = getArguments().getInt("id");

        AppDataBase db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();
        System.out.println("ID: " + id);


        lugar = db.lugarDao().findById(id);


        nombreEditado.setText(lugar.getNombre());
        localizacionEditada.setText(lugar.getLocalizacion());
        Bundle bundle = this.getArguments();
        if(fragmentOrigen.equals("tipoFragment")) {
            tipo = bundle.getString("tipoBuscado");
            tipoEditado.setText(tipo);
            elegir_Foto elegirfoto = new elegir_Foto();
            int idFoto = elegirfoto.elegirFoto(tipo);
            foto.setImageResource(idFoto);
        }
        else if (fragmentOrigen.equals("buscadoFragment") || fragmentOrigen.equals("favoritosFragment")) {
            tipoEditado.setText(lugar.getTipo());
            elegir_Foto elegirfoto = new elegir_Foto();
            int idFoto = elegirfoto.elegirFoto(lugar.getTipo());
            foto.setImageResource(idFoto);
        }
        valoracionEditada.setRating(lugar.getValoracion());
        telefonoEditado.setText(lugar.getTelefono());
        urlEditada.setText(lugar.getUrl());
        comentarioEditado.setText(lugar.getComentario());

        //Colocar foto


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
            Toast.makeText(getContext(), "Actualizado correctamente", Toast.LENGTH_SHORT).show();
            //Volver a la vista principal
            Fragment fragment = new mainFragment();
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();


        });

        eliminar.setOnClickListener(v -> db.lugarDao().delete(lugar));

        tipoEditado.setOnClickListener(v -> tipo());


        /*//Si hace click en foto_info, se abre el selector de fotos y selecciona una foto de la galeria
        foto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 10);
            //Asigna la foto seleccionada a la ImageView foto y la guarda en la base de datos
            foto.setImageURI(intent.getData());
            lugar.setFoto(intent.getData().toString());
        });*/



    }

    public void tipo(){
        String nombre = nombreEditado.getText().toString();
        String localizacion = localizacionEditada.getText().toString();
        float valoracion = valoracionEditada.getRating();
        String tipo = tipoEditado.getText().toString();
        String telefono = telefonoEditado.getText().toString();
        String url = urlEditada.getText().toString();
        String comentario = comentarioEditado.getText().toString();
        String fragmentOrigen = "editarFragment";

        Bundle result = new Bundle();
        Fragment fragment = new TipoFragment();

        result.putString("nombreBuscado", nombre);
        result.putString("localizacionBuscada", localizacion);
        result.putFloat("valoracionBuscada", valoracion);
        result.putString("tipoBuscado", tipo);
        result.putString("telefonoEditado", telefono);
        result.putString("urlEditada", url);
        result.putString("comentarioEditado", comentario);
        result.putString("fragmentOrigen", fragmentOrigen);
        result.putInt("id", id);

        fragment.setArguments(result);

        /*NavController navController = Navigation.findNavController(this.requireView());
        navController.navigate(R.id.action_mainFragment_to_buscadoFragment, result);*/

        getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
    }
}