package com.felfeit.techpedia.domain.usecase;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.domain.repository.ITechPediaRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class GetAllBrandsUseCase {
    private final ITechPediaRepository repository;

    public GetAllBrandsUseCase(ITechPediaRepository repository) {
        this.repository = repository;
    }

    public Flowable<Resource<List<Brand>>> call() {
        return repository.getAllBrands();
    }
}
