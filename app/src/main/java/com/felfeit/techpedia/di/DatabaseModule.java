package com.felfeit.techpedia.di;

import android.content.Context;

import androidx.room.Room;

import com.felfeit.techpedia.data.datasource.local.room.TechPediaDao;
import com.felfeit.techpedia.data.datasource.local.room.TechPediaDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    public static TechPediaDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, TechPediaDatabase.class, "phone_database")
                .fallbackToDestructiveMigration(false)
                .build();
    }


    @Provides
    public static TechPediaDao provideDao(TechPediaDatabase database) {
        return database.dao();
    }
}
