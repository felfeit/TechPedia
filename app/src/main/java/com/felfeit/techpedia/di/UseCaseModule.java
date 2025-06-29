package com.felfeit.techpedia.di;

import com.felfeit.techpedia.domain.repository.ITechPediaRepository;
import com.felfeit.techpedia.domain.usecase.GetAllBrandsUseCase;
import com.felfeit.techpedia.domain.usecase.GetAllSavedGadgetsUseCase;
import com.felfeit.techpedia.domain.usecase.GetBrandGadgetsUseCase;
import com.felfeit.techpedia.domain.usecase.GetGadgetSpecificationDetailUseCase;
import com.felfeit.techpedia.domain.usecase.GetLatestGadgetsUseCase;
import com.felfeit.techpedia.domain.usecase.SetSavedGadgetUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class UseCaseModule {

    @Provides
    public GetAllBrandsUseCase provideGetAllBrandsUseCase(ITechPediaRepository repository) {
        return new GetAllBrandsUseCase(repository);
    }

    @Provides
    public GetAllSavedGadgetsUseCase provideGetAllSavedPhonesUseCase(ITechPediaRepository repository) {
        return new GetAllSavedGadgetsUseCase(repository);
    }

    @Provides
    public GetLatestGadgetsUseCase provideGetLatestPhonesUseCase(ITechPediaRepository repository) {
        return new GetLatestGadgetsUseCase(repository);
    }

    @Provides
    public GetBrandGadgetsUseCase provideGetPhonesByBrandUseCase(ITechPediaRepository repository) {
        return new GetBrandGadgetsUseCase(repository);
    }

    @Provides
    public GetGadgetSpecificationDetailUseCase provideGetPhoneSpecsDetailUseCase(ITechPediaRepository repository) {
        return new GetGadgetSpecificationDetailUseCase(repository);
    }

    @Provides
    public SetSavedGadgetUseCase provideSetSavedPhoneUseCase(ITechPediaRepository repository) {
        return new SetSavedGadgetUseCase(repository);
    }
}
