package com.alberto_radno.mislugares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterLugar extends RecyclerView.Adapter<AdapterLugar.ViewHolder> {
    private List<Lugar> lugares;
    private Context context;
    private AdapterView.OnItemClickListener listener;

    public AdapterLugar(List<Lugar> lugares, Context context, AdapterView.OnItemClickListener listener) {
        this.lugares = lugares;
        this.context = context;
        this.listener = listener;
    }

    public AdapterLugar(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    public AdapterLugar(Context context, List<Lugar> lugares) {
        this.context = context;
        this.lugares = lugares;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(lugares.get(position).getNombre());
        holder.localizacion.setText(lugares.get(position).getLocalizacion());
        holder.valoracion.setRating(lugares.get(position).getValoracion());
    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView localizacion;
        RatingBar valoracion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_item);
            localizacion = itemView.findViewById(R.id.localizacion_item);
            valoracion = itemView.findViewById(R.id.ratingBar_item);
        }

        public void bind(Lugar lugar, AdapterView.OnItemClickListener listener) {
            nombre.setText(lugar.getNombre());
            localizacion.setText(lugar.getLocalizacion());
            valoracion.setRating(lugar.getValoracion());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(null, v, getAdapterPosition(), v.getId());
                }
            });
        }
    }
}
