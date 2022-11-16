package com.alberto_radno.mislugares;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface LugarDao {
    @Insert
    void insert(Lugar lugar);
    @Update
    void update(Lugar lugar);
    @Delete
    void delete(Lugar lugar);
    @Query("SELECT * FROM tabla_mis_lugares")
    List<Lugar> findAll();
    @Query("SELECT * FROM tabla_mis_lugares WHERE id = :id")
    Lugar findById(int id);
    @Query("SELECT * FROM tabla_mis_lugares WHERE nombre = :nombre")
    Lugar findByName(String nombre);
    @Query("SELECT * FROM tabla_mis_lugares WHERE localizacion = :localizacion")
    Lugar findByLocation(String localizacion);
    @Query("SELECT * FROM tabla_mis_lugares WHERE valoracion = :valoracion")
    Lugar findByRating(float valoracion);
    //Encontrar todos los lugares con el nombre indicado
    @Query("SELECT * FROM tabla_mis_lugares WHERE nombre LIKE :nombre")
    List<Lugar> findAllByName(String nombre);
    //Encontrar todos los lugares con la localización indicada
    @Query("SELECT * FROM tabla_mis_lugares WHERE localizacion LIKE :localizacion")
    List<Lugar> findAllByLocation(String localizacion);
    //Encontrar todos los lugares con la valoración indicada
    @Query("SELECT * FROM tabla_mis_lugares WHERE valoracion LIKE :valoracion")
    List<Lugar> findAllByRating(float valoracion);
}
