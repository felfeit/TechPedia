package com.felfeit.techpedia.presentation.pages.saved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.usecase.GetAllSavedGadgetsUseCase;
import com.felfeit.techpedia.domain.usecase.SetSavedGadgetUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class SavedGadgetsViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public LiveData<List<Gadget>> phones;
    private final GetAllSavedGadgetsUseCase getAllSavedGadgetsUseCase;
    private final SetSavedGadgetUseCase setSavedGadgetUseCase;

    @Inject
    public SavedGadgetsViewModel(
            GetAllSavedGadgetsUseCase getAllSavedGadgetsUseCase,
            SetSavedGadgetUseCase setSavedGadgetUseCase
    ) {
        this.getAllSavedGadgetsUseCase = getAllSavedGadgetsUseCase;
        this.setSavedGadgetUseCase = setSavedGadgetUseCase;
        fetchSavedPhones();
    }

    public void setSavedPhone(Gadget phone, boolean newState) {
        phone.setSaved(newState);
        compositeDisposable.add(setSavedGadgetUseCase.call(phone, newState)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        );
        fetchSavedPhones();
    }

    private void fetchSavedPhones() {
        phones = LiveDataReactiveStreams.fromPublisher(getAllSavedGadgetsUseCase.call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        );
    }
}
