package com.threesharp.personabook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Persona.class}, version = 1)
public abstract class PersonaDatabase extends RoomDatabase {
    private static PersonaDatabase INSTANCE = null;
    public abstract PersonaDao personaDao();

    public synchronized static PersonaDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PersonaDatabase.class, "persona.db").build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
