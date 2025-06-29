package com.felfeit.techpedia.data.datasource.local.room;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.felfeit.techpedia.data.datasource.local.entity.BrandEntity;
import com.felfeit.techpedia.data.datasource.local.entity.GadgetEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TechPediaDao {

    @Query("SELECT * FROM brand")
    Flowable<List<BrandEntity>> getAllBrands();

    @Query("SELECT * FROM gadget")
    Flowable<List<GadgetEntity>> getAllGadgets();

    @Query("SELECT * FROM gadget WHERE is_saved = 1")
    Flowable<List<GadgetEntity>> getSavedGadgets();

    @Upsert
    Completable insertBrands(List<BrandEntity> brands);

    @Upsert
    Completable insertGadgets(List<GadgetEntity> gadgets);

    @Upsert
    Completable updateSavedGadget(GadgetEntity entity);
}
