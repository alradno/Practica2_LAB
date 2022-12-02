package com.alberto_radno.mislugares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterLugar extends RecyclerView.Adapter<AdapterLugar.ViewHolder> {
    private List<Lugar> lugares;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    private elegir_Foto elegirFoto;
    private static Button favorito;


    public AdapterLugar(Context context, List<Lugar> lugares, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
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
        elegirFoto = new elegir_Foto();
        int id = elegirFoto.elegirFoto(lugares.get(position).getTipo());
        holder.foto.setImageResource(id);
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

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_item);
            localizacion = itemView.findViewById(R.id.localizacion_item);
            valoracion = itemView.findViewById(R.id.ratingBar_item);
            tipo = itemView.findViewById(R.id.tipo_item);
            foto = itemView.findViewById(R.id.foto_item);
            //favorito = itemView.findViewById(R.id.favoritoButton);

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

            /*favorito.setOnClickListener(v -> {
                if(recyclerViewInterface != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onFavoritoClick(position);
                    }
                }
            });*/


        }

    }
}
