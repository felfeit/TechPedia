package com.felfeit.techpedia.presentation.pages.browse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.domain.usecase.GetAllBrandsUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class BrowseBrandViewModel extends ViewModel {
    public final LiveData<Resource<List<Brand>>> brands;

    @Inject
    public BrowseBrandViewModel(GetAllBrandsUseCase useCase) {
        brands = LiveDataReactiveStreams.fromPublisher(useCase.call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        );
    }
}
