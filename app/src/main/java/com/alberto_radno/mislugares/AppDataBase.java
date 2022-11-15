package com.alberto_radno.mislugares;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Lugar.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract LugarDao lugarDao();
}
