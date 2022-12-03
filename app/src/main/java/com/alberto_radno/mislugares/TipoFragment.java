package com.alberto_radno.mislugares;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class TipoFragment extends Fragment implements View.OnClickListener {

    String nombreBuscado;
    String localizacionBuscada;
    float valoracionBuscada;
    String tipoBuscado;


    public TipoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        //Recogemos los datos del fragment mainFragment rellenos hasta ahora
        if (bundle != null) {
            nombreBuscado = bundle.getString("nombreBuscado");
            localizacionBuscada = bundle.getString("localizacionBuscada");
            valoracionBuscada = bundle.getFloat("valoracionBuscada");
            tipoBuscado = bundle.getString("tipoBuscado");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tipo, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton b1 = view.findViewById(R.id.imageButton);
        b1.setOnClickListener(this);
        ImageButton b2 = view.findViewById(R.id.imageButton2);
        b2.setOnClickListener(this);
        ImageButton b3 = view.findViewById(R.id.imageButton3);
        b3.setOnClickListener(this);
        ImageButton b4 = view.findViewById(R.id.imageButton4);
        b4.setOnClickListener(this);
        ImageButton b5 = view.findViewById(R.id.imageButton5);
        b5.setOnClickListener(this);
        ImageButton b6 = view.findViewById(R.id.imageButton6);
        b6.setOnClickListener(this);
        ImageButton b7 = view.findViewById(R.id.imageButton7);
        b7.setOnClickListener(this);
        ImageButton b8 = view.findViewById(R.id.imageButton8);
        b8.setOnClickListener(this);
        ImageButton b9 = view.findViewById(R.id.imageButton9);
        b9.setOnClickListener(this);
        ImageButton b10 = view.findViewById(R.id.imageButton10);
        b10.setOnClickListener(this);
        ImageButton b11 = view.findViewById(R.id.imageButton11);
        b11.setOnClickListener(this);
        ImageButton b12 = view.findViewById(R.id.imageButton12);
        b12.setOnClickListener(this);
        ImageButton b13 = view.findViewById(R.id.imageButton13);
        b13.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                tipoBuscado = "argentino";
                break;
            case R.id.imageButton2:
                tipoBuscado = "bar";
                break;
            case R.id.imageButton3:
                tipoBuscado = "casero";
                break;
            case R.id.imageButton4:
                tipoBuscado = "coctel";
                break;
            case R.id.imageButton5:
                tipoBuscado = "fast food";
                break;
            case R.id.imageButton6:
                tipoBuscado = "gallego";
                break;
            case R.id.imageButton7:
                tipoBuscado = "italiano";
                break;
            case R.id.imageButton8:
                tipoBuscado = "japones";
                break;
            case R.id.imageButton9:
                tipoBuscado = "mexicano";
                break;
            case R.id.imageButton10:
                tipoBuscado = "sushi";
                break;
            case R.id.imageButton11:
                tipoBuscado = "tapas";
                break;
            case R.id.imageButton12:
                tipoBuscado = "valenciano";
                break;
            case R.id.imageButton13:
                tipoBuscado = "vegano";
                break;
        }
        //Abrir el fragmento de mainFragment
        mainFragment fragment = new mainFragment();
        //Devolvemos los datos a mainFragment
        Bundle bundle = new Bundle();
        bundle.putString("nombreBuscado", nombreBuscado);
        bundle.putString("localizacionBuscada", localizacionBuscada);
        bundle.putFloat("valoracionBuscada", valoracionBuscada);
        bundle.putString("tipoBuscado", tipoBuscado);
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
    }
}