package com.threesharp.personabook;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PersonaDao {
    @Query("SELECT * FROM Persona")
    List<Persona> getAll();
    @Query("SELECT * FROM Persona WHERE type IN (:type)")
    List<Persona> load(int type);
    @Insert
    void insert(Persona persona);
    @Delete
    void delete(Persona persona);
}
