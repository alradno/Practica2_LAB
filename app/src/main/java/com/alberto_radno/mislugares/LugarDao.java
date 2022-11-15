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
}
