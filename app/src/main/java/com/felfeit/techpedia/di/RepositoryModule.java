package com.felfeit.techpedia.di;

import com.felfeit.techpedia.data.datasource.local.TechPediaLocalDataSource;
import com.felfeit.techpedia.data.datasource.remote.TechPediaRemoteDataSource;
import com.felfeit.techpedia.data.datasource.repository.TechPediaRepository;
import com.felfeit.techpedia.domain.repository.ITechPediaRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public ITechPediaRepository provideTechPediaRepository(
            TechPediaRemoteDataSource remoteDataSource,
            TechPediaLocalDataSource localDataSource
    ) {
        return new TechPediaRepository(remoteDataSource, localDataSource);
    }
}
