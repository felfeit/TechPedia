package com.felfeit.techpedia.domain.usecase;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.repository.ITechPediaRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class GetBrandGadgetsUseCase {
    private final ITechPediaRepository repository;

    public GetBrandGadgetsUseCase(ITechPediaRepository repository) {
        this.repository = repository;
    }

    public Flowable<Resource<List<Gadget>>> call(String brandSlug) {
        return repository.getBrandPhones(brandSlug);
    }
}
