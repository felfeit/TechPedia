package com.felfeit.techpedia.presentation.pages.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.models.GadgetSpecificationDetail;
import com.felfeit.techpedia.domain.usecase.GetGadgetSpecificationDetailUseCase;
import com.felfeit.techpedia.domain.usecase.SetSavedGadgetUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class PhoneDetailViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final GetGadgetSpecificationDetailUseCase getGadgetSpecificationDetailUseCase;
    private final SetSavedGadgetUseCase setSavedGadgetUseCase;
    public LiveData<Resource<GadgetSpecificationDetail>> phoneDetail;

    @Inject
    public PhoneDetailViewModel(GetGadgetSpecificationDetailUseCase getGadgetSpecificationDetailUseCase, SetSavedGadgetUseCase setSavedGadgetUseCase) {
        this.getGadgetSpecificationDetailUseCase = getGadgetSpecificationDetailUseCase;
        this.setSavedGadgetUseCase = setSavedGadgetUseCase;
    }

    public void loadPhoneSpecDetail(String phoneSlug) {
        phoneDetail = LiveDataReactiveStreams.fromPublisher(getGadgetSpecificationDetailUseCase.call(phoneSlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        );
    }

    public void setSavedPhone(Gadget phone, boolean newState) {
        phone.setSaved(newState);
        compositeDisposable.add(setSavedGadgetUseCase.call(phone, newState)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
