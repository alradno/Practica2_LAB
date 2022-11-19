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
    @ColumnInfo(name = "tipo")
    public String tipo;
    @ColumnInfo(name = "telefono")
    public String telefono;
    @ColumnInfo(name = "url")
    public String url;
    @ColumnInfo(name = "comentario")
    public String comentario;
    @ColumnInfo(name = "foto")
    public String foto;


    public Lugar(String nombre, String localizacion, float valoracion, String tipo) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.valoracion = valoracion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    /*public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }*/

    public String getComentario() { return comentario; }

    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }
}
