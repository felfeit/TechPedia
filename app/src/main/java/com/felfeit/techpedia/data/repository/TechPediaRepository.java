package com.felfeit.techpedia.data.repository;

import com.felfeit.techpedia.data.NetworkBoundResource;
import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.data.datasource.local.TechPediaLocalDataSource;
import com.felfeit.techpedia.data.datasource.local.entity.BrandEntity;
import com.felfeit.techpedia.data.datasource.local.entity.GadgetEntity;
import com.felfeit.techpedia.data.datasource.remote.TechPediaRemoteDataSource;
import com.felfeit.techpedia.data.datasource.remote.dto.BrandGadgetsDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.BrandsDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetSpecsData;
import com.felfeit.techpedia.data.datasource.remote.network.ApiResponse;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.models.GadgetSpecificationDetail;
import com.felfeit.techpedia.domain.repository.ITechPediaRepository;
import com.felfeit.techpedia.utils.Mapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class TechPediaRepository implements ITechPediaRepository {

    private final TechPediaRemoteDataSource remoteDataSource;
    private final TechPediaLocalDataSource localDataSource;

    @Inject
    public TechPediaRepository(TechPediaRemoteDataSource remoteDataSource, TechPediaLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Flowable<Resource<List<Brand>>> getAllBrands() {
        return new NetworkBoundResource<List<Brand>, List<BrandsDtoItem>>() {

            @Override
            protected Flowable<List<Brand>> loadFromDB() {
                return localDataSource.getAllBrands().map(Mapper::mapBrandEntityListToDomainList);
            }

            @Override
            protected boolean shouldFetch(List<Brand> data) {
                return data == null || data.isEmpty();
            }

            @Override
            protected Flowable<ApiResponse<List<BrandsDtoItem>>> createCall() {
                return remoteDataSource.getAllBrands();
            }

            @Override
            protected void saveCallResult(List<BrandsDtoItem> data) {
                List<BrandEntity> entities = Mapper.mapBrandDtoListToEntityList(data);
                localDataSource.insertBrands(entities).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
            }
        }.asFlowable();
    }

    @Override
    public Flowable<Resource<List<Gadget>>> getBrandPhones(String brandSlug) {
        return Flowable.concat(Flowable.just(Resource.loading()), remoteDataSource.getBrandPhones(brandSlug).subscribeOn(Schedulers.io()).map(response -> {
            if (response instanceof ApiResponse.Success) {
                List<BrandGadgetsDtoItem> data = ((ApiResponse.Success<List<BrandGadgetsDtoItem>>) response).data;
                return Resource.success(Mapper.mapBrandGadgetDtoListToDomainList(data));
            } else if (response instanceof ApiResponse.Error) {
                String errorMsg = ((ApiResponse.Error<?>) response).errorMessage;
                return Resource.error(errorMsg);
            } else {
                return Resource.error("Unknown error occurred");
            }
        }));
    }

    @Override
    public Flowable<Resource<List<Gadget>>> getLatestPhones() {
        return Flowable.concat(
                Flowable.just(Resource.loading()),
                remoteDataSource.getLatestPhones()
                        .subscribeOn(Schedulers.io())
                        .flatMap(this::handleApiResponse)
        );
    }

    @Override
    public Flowable<List<Gadget>> getSavedPhones() {
        return localDataSource.getSavedPhones().map(Mapper::mapGadgetEntityListToDomainList);
    }

    @Override
    public Flowable<Resource<GadgetSpecificationDetail>> getPhoneSpecsDetail(String phoneSlug) {
        return Flowable.concat(
                Flowable.just(Resource.loading()),
                remoteDataSource
                        .getPhoneSpecsDetail(phoneSlug)
                        .subscribeOn(Schedulers.io())
                        .map(response -> {
                            if (response instanceof ApiResponse.Success) {
                                GadgetSpecsData data = ((ApiResponse.Success<GadgetSpecsData>) response).data;
                                GadgetSpecificationDetail gadgetSpecificationDetail = Mapper.mapToDomain(data);
                                return Resource.success(gadgetSpecificationDetail);
                            } else if (response instanceof ApiResponse.Empty) {
                                return Resource.error("No data found");
                            } else if (response instanceof ApiResponse.Error) {
                                String error = ((ApiResponse.Error<?>) response).errorMessage;
                                return Resource.error(error);
                            } else {
                                return Resource.error("Unknown error occurred");
                            }
                        }));
    }

    @Override
    public Completable setSavedPhone(Gadget phone, boolean newState) {
        GadgetEntity entity = Mapper.mapGadgetDomainToEntity(phone);
        return localDataSource.setSavedGadget(entity, newState);
    }

    private Flowable<Resource<List<Gadget>>> handleApiResponse(ApiResponse<List<GadgetDtoItem>> apiResponse) {
        if (apiResponse instanceof ApiResponse.Success) {
            List<GadgetDtoItem> data = ((ApiResponse.Success<List<GadgetDtoItem>>) apiResponse).data;
            List<Gadget> latestPhones = Mapper.mapLatestGadgetDtoListToDomainList(data);
            return mergeWithSavedPhones(latestPhones);
        } else if (apiResponse instanceof ApiResponse.Empty) {
            return Flowable.just(Resource.error("No data found"));

        } else if (apiResponse instanceof ApiResponse.Error) {
            String error = ((ApiResponse.Error<?>) apiResponse).errorMessage;
            return Flowable.just(Resource.error(error));

        } else {
            return Flowable.just(Resource.error("Unknown error occurred"));
        }
    }

    private Flowable<Resource<List<Gadget>>> mergeWithSavedPhones(List<Gadget> latestPhones) {
        return localDataSource.getSavedPhones()
                .firstOrError()
                .map(savedPhones -> {
                    markSavedPhones(latestPhones, savedPhones);
                    return Resource.success(latestPhones);
                })
                .toFlowable();
    }

    private void markSavedPhones(List<Gadget> latestPhones, List<GadgetEntity> savedPhones) {
        for (Gadget phone : latestPhones) {
            for (GadgetEntity entity : savedPhones) {
                if (phone.getSlug().equals(entity.getSlug())) {
                    phone.setSaved(true);
                    break;
                }
            }
        }
    }
}
