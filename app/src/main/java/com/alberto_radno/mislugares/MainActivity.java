package com.alberto_radno.mislugares;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.alberto_radno.mislugares.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //Boton buscar por defecto
        binding.navigationView.getMenu().getItem(1).setChecked(true);
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Mostrar el icono de la app en el action bar
        getSupportActionBar().setLogo(R.mipmap.icono_app);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //Si pulsa en el icono de buscar del navigation bar, se abre el fragment buscadoFragment
        binding.navigationView.setOnItemSelectedListener(item -> {
            //Boton por favoritosButton por defecto marcado
            switch(item.getItemId()) {
                case R.id.favoritosButton:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new favoritosFragment()).commit();
                    break;
                case R.id.buscarButton:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new mainFragment()).commit();
                    break;
                case R.id.infoButton:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new manualFragment()).commit();
                    break;
            }
            return true;
        });


    }
}