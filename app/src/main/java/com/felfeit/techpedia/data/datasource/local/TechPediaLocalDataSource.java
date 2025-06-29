package com.felfeit.techpedia.data.datasource.local;

import androidx.annotation.NonNull;

import com.felfeit.techpedia.data.datasource.local.entity.BrandEntity;
import com.felfeit.techpedia.data.datasource.local.entity.GadgetEntity;
import com.felfeit.techpedia.data.datasource.local.room.TechPediaDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Singleton
public class TechPediaLocalDataSource {

    private final TechPediaDao dao;

    @Inject
    TechPediaLocalDataSource(TechPediaDao dao) {
        this.dao = dao;
    }

    public Flowable<List<GadgetEntity>> getAllGadgets() {
        return dao.getAllGadgets();
    }

    public Flowable<List<BrandEntity>> getAllBrands() {
        return dao.getAllBrands();
    }

    public Flowable<List<GadgetEntity>> getSavedPhones() {
        return dao.getSavedGadgets();
    }

    public Completable insertBrands(List<BrandEntity> brands) {
        return dao.insertBrands(brands);
    }

    public Completable insertGadgets(List<GadgetEntity> phones) {
        return dao.insertGadgets(phones);
    }

    public Completable setSavedGadget(@NonNull GadgetEntity gadgetEntity, boolean newState) {
        gadgetEntity.setSaved(newState);
        return dao.updateSavedGadget(gadgetEntity);
    }
}
