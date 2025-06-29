package com.felfeit.techpedia.presentation.pages.gadgets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.usecase.GetBrandGadgetsUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class BrandGadgetsViewModel extends ViewModel {

    private final GetBrandGadgetsUseCase useCase;
    public LiveData<Resource<List<Gadget>>> brandPhones;

    @Inject
    public BrandGadgetsViewModel(GetBrandGadgetsUseCase useCase) {
        this.useCase = useCase;
    }

    public void loadBrandPhones(String brandSlug) {
        brandPhones = LiveDataReactiveStreams.fromPublisher(
                useCase.call(brandSlug)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }
}
