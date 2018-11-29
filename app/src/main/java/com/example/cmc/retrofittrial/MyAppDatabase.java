package com.example.cmc.retrofittrial;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Employee.class,version = 4)
public abstract class MyAppDatabase extends RoomDatabase{
    public abstract MyDao myDao();
}
