package com.alberto_radno.mislugares;

public class elegir_Foto {

    public int elegirFoto(String tipoRestaurante) {
        int id=0;
        //convertir a minusculas
        tipoRestaurante=tipoRestaurante.toLowerCase();
        switch (tipoRestaurante) {
            case "argentino":
                id = R.drawable.argentino;
                break;
            case "bar":
                id = R.drawable.bar;
                break;
            case "casero":
                id = R.drawable.casero;
                break;
            case "coctel":
                id = R.drawable.coctel;
                break;
            case "fast food":
                id = R.drawable.fast_food;
                break;
            case "gallego":
                id = R.drawable.gallego;
                break;
            case "italiano":
                id = R.drawable.italiano;
                break;
            case "japones":
                id = R.drawable.japones;
                break;
            case "mexicano":
                id = R.drawable.mexicano;
                break;
            case "sushi":
                id = R.drawable.sushi;
                break;
            case "tapas":
                id = R.drawable.tapas;
                break;
            case "valenciano":
                id = R.drawable.valenciano;
                break;
            case "vegano":
                id = R.drawable.vegano;
                break;
        }
        return id;
    }
}
