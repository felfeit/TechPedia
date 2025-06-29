package com.felfeit.techpedia.domain.repository;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.models.GadgetSpecificationDetail;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface ITechPediaRepository {
    Flowable<Resource<List<Brand>>> getAllBrands();
    Flowable<Resource<List<Gadget>>> getBrandPhones(String brandSlug);
    Flowable<Resource<List<Gadget>>> getLatestPhones();
    Flowable<List<Gadget>> getSavedPhones();
    Flowable<Resource<GadgetSpecificationDetail>> getPhoneSpecsDetail(String phoneSlug);
    Completable setSavedPhone(Gadget phone, boolean newState);
}
