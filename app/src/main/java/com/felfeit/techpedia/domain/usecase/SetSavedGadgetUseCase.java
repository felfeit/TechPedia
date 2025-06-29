package com.felfeit.techpedia.domain.usecase;

import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.repository.ITechPediaRepository;

import io.reactivex.rxjava3.core.Completable;

public class SetSavedGadgetUseCase {
    private final ITechPediaRepository repository;

    public SetSavedGadgetUseCase(ITechPediaRepository repository) {
        this.repository = repository;
    }

    public Completable call(Gadget phone, boolean newState) {
        return repository.setSavedPhone(phone, newState);
    }
}
