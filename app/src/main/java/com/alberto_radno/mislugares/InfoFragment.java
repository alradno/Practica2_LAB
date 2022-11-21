package com.alberto_radno.mislugares;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    Lugar lugar;
    TextView nombre;
    TextView localizacion;
    TextView tipo;
    RatingBar valoracion;
    TextView comentario;
    TextView telefono;
    int id;

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("buscado_a_info", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                id = result.getInt("id");
                System.out.println("id recibido: " + id);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        int id = bundle.getInt("id");

        AppDataBase db = Room.databaseBuilder(requireContext(), AppDataBase.class, "lugares").allowMainThreadQueries().build();
        lugar = db.lugarDao().findById(id);

        nombre = view.findViewById(R.id.nombre_info);
        localizacion = view.findViewById(R.id.localizacion_info);
        tipo = view.findViewById(R.id.tipo_info);
        valoracion = view.findViewById(R.id.ratingBar_info);
        comentario = view.findViewById(R.id.comentario_info);
        telefono = view.findViewById(R.id.telefono_info);

        nombre.setText(lugar.getNombre());
        localizacion.setText(lugar.getLocalizacion());
        tipo.setText(lugar.getTipo());
        valoracion.setRating(lugar.getValoracion());
        comentario.setText(lugar.getComentario());
        telefono.setText(lugar.getTelefono());


    }
}