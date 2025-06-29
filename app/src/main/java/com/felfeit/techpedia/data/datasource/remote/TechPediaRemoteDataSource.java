package com.felfeit.techpedia.data.datasource.remote;

import android.annotation.SuppressLint;
import android.util.Log;

import com.felfeit.techpedia.data.datasource.remote.dto.BrandsDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.BrandGadgetsDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetSpecsData;
import com.felfeit.techpedia.data.datasource.remote.network.ApiResponse;
import com.felfeit.techpedia.data.datasource.remote.network.ApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

@Singleton
public class TechPediaRemoteDataSource {

    private final ApiService apiService;

    @Inject
    TechPediaRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @SuppressLint("CheckResult")
    public Flowable<ApiResponse<List<BrandsDtoItem>>> getAllBrands() {
        PublishSubject<ApiResponse<List<BrandsDtoItem>>> resultData = PublishSubject.create();

        apiService.getBrands().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).take(1).subscribe(response -> {
            List<BrandsDtoItem> data = response.getData();
            if (data != null && !data.isEmpty()) {
                resultData.onNext(new ApiResponse.Success<>(data));
            } else {
                resultData.onNext(new ApiResponse.Empty<>());
            }
        }, error -> {
            resultData.onNext(new ApiResponse.Error<>(error.getMessage()));
            Log.e("RemoteDataSource", error.toString());
        });

        return resultData.toFlowable(BackpressureStrategy.BUFFER);
    }

    @SuppressLint("CheckResult")
    public Flowable<ApiResponse<List<BrandGadgetsDtoItem>>> getBrandPhones(String brandSlug) {
        PublishSubject<ApiResponse<List<BrandGadgetsDtoItem>>> resultData = PublishSubject.create();

        apiService.getBrandGadgets(brandSlug).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).take(1).subscribe(response -> {
            List<BrandGadgetsDtoItem> data = response.getData().getPhones();
            if (data != null && !data.isEmpty()) {
                resultData.onNext(new ApiResponse.Success<>(data));
            } else {
                resultData.onNext(new ApiResponse.Empty<>());
            }
        }, error -> {
            resultData.onNext(new ApiResponse.Error<>(error.getMessage()));
            Log.e("RemoteDataSource", error.toString());
        });

        return resultData.toFlowable(BackpressureStrategy.BUFFER);
    }

    @SuppressLint("CheckResult")
    public Flowable<ApiResponse<List<GadgetDtoItem>>> getLatestPhones() {
        PublishSubject<ApiResponse<List<GadgetDtoItem>>> resultData = PublishSubject.create();

        apiService.getLatestGadgets().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).take(1).subscribe(response -> {
            List<GadgetDtoItem> data = response.getData().getPhones();
            if (response.isStatus()) {
                resultData.onNext(new ApiResponse.Success<>(data));
            } else {
                resultData.onNext(new ApiResponse.Empty<>());
            }
        }, error -> {
            resultData.onNext(new ApiResponse.Error<>(error.getMessage()));
            Log.e("RemoteDataSource", error.toString());
        });

        return resultData.toFlowable(BackpressureStrategy.BUFFER);
    }

    @SuppressLint("CheckResult")
    public Flowable<ApiResponse<GadgetSpecsData>> getPhoneSpecsDetail(String phoneSlug) {
        PublishSubject<ApiResponse<GadgetSpecsData>> resultData = PublishSubject.create();

        apiService.getGadgetSpecificationDetail(phoneSlug).subscribeOn(Schedulers.computation()).subscribeOn(AndroidSchedulers.mainThread()).take(1).subscribe(response -> {
            GadgetSpecsData data = response.getData();
            if (response.isStatus()) {
                resultData.onNext(new ApiResponse.Success<>(data));
            } else {
                resultData.onNext(new ApiResponse.Empty<>());
            }
        }, error -> {
            resultData.onNext(new ApiResponse.Error<>(error.getMessage()));
            Log.e("RemoteDataSource", error.toString());
        });

        return resultData.toFlowable(BackpressureStrategy.BUFFER);
    }
}
