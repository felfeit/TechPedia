package com.felfeit.techpedia.domain.usecase;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.GadgetSpecificationDetail;
import com.felfeit.techpedia.domain.repository.ITechPediaRepository;

import io.reactivex.rxjava3.core.Flowable;

public class GetGadgetSpecificationDetailUseCase {
    private final ITechPediaRepository repository;

    public GetGadgetSpecificationDetailUseCase(ITechPediaRepository repository) {
        this.repository = repository;
    }

    public Flowable<Resource<GadgetSpecificationDetail>> call(String phoneSlug) {
        return repository.getPhoneSpecsDetail(phoneSlug);
    }
}
