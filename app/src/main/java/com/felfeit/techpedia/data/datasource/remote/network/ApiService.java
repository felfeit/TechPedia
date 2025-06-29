package com.felfeit.techpedia.data.datasource.remote.network;

import com.felfeit.techpedia.data.datasource.remote.dto.BrandGadgetsDto;
import com.felfeit.techpedia.data.datasource.remote.dto.BrandsDto;
import com.felfeit.techpedia.data.datasource.remote.dto.LatestGadgetsDto;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetSpecsDto;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("brands")
    Flowable<BrandsDto> getBrands();

    @GET("latest")
    Flowable<LatestGadgetsDto> getLatestGadgets();

    @GET("brands/{brand_slug}")
    Flowable<BrandGadgetsDto> getBrandGadgets(@Path("brand_slug") String brandSlug);

    @GET("{phone_slug}")
    Flowable<GadgetSpecsDto> getGadgetSpecificationDetail(@Path("phone_slug") String phoneSlug);
}
