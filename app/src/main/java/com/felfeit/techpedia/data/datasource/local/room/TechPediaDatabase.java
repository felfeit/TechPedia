package com.felfeit.techpedia.data.datasource.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.felfeit.techpedia.data.datasource.local.entity.BrandEntity;
import com.felfeit.techpedia.data.datasource.local.entity.GadgetEntity;

@Database(entities = {BrandEntity.class, GadgetEntity.class}, version = 3, exportSchema = false)
public abstract class TechPediaDatabase extends RoomDatabase {
    public abstract TechPediaDao dao();
}
