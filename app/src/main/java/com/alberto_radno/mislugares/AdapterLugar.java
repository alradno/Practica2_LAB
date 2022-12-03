package com.alberto_radno.mislugares;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterLugar extends RecyclerView.Adapter<AdapterLugar.ViewHolder> {
    private final List<Lugar> lugares;
    private final RecyclerViewInterface recyclerViewInterface;


    public AdapterLugar(Context context, List<Lugar> lugares, RecyclerViewInterface recyclerViewInterface) {
        this.lugares = lugares;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugar, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(lugares.get(position).getNombre());
        holder.localizacion.setText(lugares.get(position).getLocalizacion());
        holder.valoracion.setRating(lugares.get(position).getValoracion());
        holder.tipo.setText(lugares.get(position).getTipo());
        //Glide.with(context).load(lugares.get(position).getFoto()).into(holder.foto);
        elegir_Foto elegirFoto = new elegir_Foto();
        int id = elegirFoto.elegirFoto(lugares.get(position).getTipo());
        holder.foto.setImageResource(id);

        if(lugares.get(position).favorito){
            holder.favorito.setImageResource(R.drawable.favorito);
        }

        //Si es el ultimo elemento de la lista pone un espacio en blanco
        if(position == lugares.size()-1){
            holder.espacio.setVisibility(View.VISIBLE);
        }
        else{
            holder.espacio.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView localizacion;
        RatingBar valoracion;
        TextView tipo;
        ImageView foto;
        ImageButton favorito;
        Space espacio;

        @SuppressLint("UseCompatLoadingForDrawables")
        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_item);
            localizacion = itemView.findViewById(R.id.localizacion_item);
            valoracion = itemView.findViewById(R.id.ratingBar_item);
            tipo = itemView.findViewById(R.id.tipo_item);
            foto = itemView.findViewById(R.id.foto_item);
            favorito = itemView.findViewById(R.id.favoritoButton);
            espacio = itemView.findViewById(R.id.espacio);

            itemView.setOnClickListener(v -> {
               if(recyclerViewInterface != null){
                   int position = getAdapterPosition();
                   if(position != RecyclerView.NO_POSITION){
                       recyclerViewInterface.onItemClick(position);
                   }
               }
            });

            itemView.setOnLongClickListener(v -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onLongItemClick(position);
                    }
                }
                return true;
            });

            favorito.setOnClickListener(v -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onFavoritoClick(position);
                    }
                    //Si la imagen es favorito, la cambia a no favorito y viceversa
                    if(favorito.getDrawable().getConstantState() == itemView.getResources().getDrawable(R.drawable.favorito).getConstantState()){
                        favorito.setImageResource(R.drawable.no_favorito);
                    }else{
                        favorito.setImageResource(R.drawable.favorito);
                    }
                }
            });


        }

    }
}
