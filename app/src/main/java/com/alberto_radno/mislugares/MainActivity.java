package com.alberto_radno.mislugares;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.os.Bundle;

import com.alberto_radno.mislugares.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Mostrar el icono de la app en el action bar
        getSupportActionBar().setLogo(R.mipmap.icono_app);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Si pulsa en el icono de buscar del navigation bar, se abre el fragment buscadoFragment
        binding.navigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.favoritosButton:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new buscadoFragment()).commit();
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