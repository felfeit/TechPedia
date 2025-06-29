package com.felfeit.techpedia.domain.usecase;

import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.repository.ITechPediaRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class GetAllSavedGadgetsUseCase {
    private final ITechPediaRepository repository;

    public GetAllSavedGadgetsUseCase(ITechPediaRepository repository) {
        this.repository = repository;
    }

    public Flowable<List<Gadget>> call() {
        return repository.getSavedPhones();
    }
}
