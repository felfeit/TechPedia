package com.felfeit.techpedia.presentation.pages.latest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.usecase.GetLatestGadgetsUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class LatestGadgetsViewModel extends ViewModel {

    public final LiveData<Resource<List<Gadget>>> phones;
    @Inject
    public LatestGadgetsViewModel(GetLatestGadgetsUseCase useCase) {
        phones = LiveDataReactiveStreams.fromPublisher(useCase.call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        );
    }
}
