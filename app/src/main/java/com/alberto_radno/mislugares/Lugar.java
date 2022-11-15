package com.alberto_radno.mislugares;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_mis_lugares")
public class Lugar {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "localizacion")
    public String localizacion;
    @ColumnInfo(name = "valoracion")
    public float valoracion;


    public Lugar(String nombre, String localizacion, float valoracion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.valoracion = valoracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    /*public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /*public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }*/
}
