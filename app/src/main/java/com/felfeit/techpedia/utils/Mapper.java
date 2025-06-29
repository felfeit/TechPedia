package com.felfeit.techpedia.utils;

import com.felfeit.techpedia.data.datasource.local.entity.BrandEntity;
import com.felfeit.techpedia.data.datasource.local.entity.GadgetEntity;
import com.felfeit.techpedia.data.datasource.remote.dto.BrandGadgetsDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.BrandsDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetDtoItem;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetSpecificationsItem;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetSpecsData;
import com.felfeit.techpedia.data.datasource.remote.dto.GadgetSpecsItem;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.models.GadgetSpecificationDetail;
import com.felfeit.techpedia.domain.models.GadgetSpecification;
import com.felfeit.techpedia.domain.models.SpecificationDetail;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static GadgetSpecificationDetail mapToDomain(GadgetSpecsData dto) {
        if (dto == null) return null;

        GadgetSpecificationDetail domain = new GadgetSpecificationDetail();
        domain.setBrand(dto.getBrand());
        domain.setGadgetName(dto.getPhoneName());
        domain.setThumbnail(dto.getThumbnail());
        domain.setPhoneImages(dto.getPhoneImages());
        domain.setReleaseDate(dto.getReleaseDate());
        domain.setDimension(dto.getDimension());
        domain.setOs(dto.getOs());
        domain.setStorage(dto.getStorage());

        List<GadgetSpecification> specList = new ArrayList<>();
        if (dto.getSpecifications() != null) {
            for (GadgetSpecificationsItem specItem : dto.getSpecifications()) {
                GadgetSpecification spec = new GadgetSpecification();
                spec.setTitle(specItem.getTitle());

                List<SpecificationDetail> subSpecs = new ArrayList<>();
                for (GadgetSpecsItem item : specItem.getSpecs()) {
                    SpecificationDetail s = new SpecificationDetail();
                    s.setKey(item.getKey());
                    s.setVal(item.getVal());
                    subSpecs.add(s);
                }

                spec.setSpecs(subSpecs);
                specList.add(spec);
            }
        }

        domain.setSpecifications(specList);
        return domain;
    }

    // Brand DTO To Brand Entity
    public static BrandEntity mapBrandDtoToEntity(BrandsDtoItem dto) {
        BrandEntity entity = new BrandEntity();
        entity.setBrandName(dto.getBrandName());
        entity.setBrandSlug(dto.getBrandSlug());
        entity.setDeviceCount(dto.getDeviceCount());
        return entity;
    }

    // Brand Entity To Brand Domain
    public static Brand mapBrandEntityToDomain(BrandEntity entity) {
        Brand domain = new Brand();
        domain.setName(entity.getBrandName());
        domain.setSlug(entity.getBrandSlug());
        domain.setDeviceCount(entity.getDeviceCount());
        return domain;
    }

    // Brand DTO List to Brand Entity List
    public static List<BrandEntity> mapBrandDtoListToEntityList(List<BrandsDtoItem> dtos) {
        List<BrandEntity> entities = new ArrayList<>();
        for (BrandsDtoItem dto : dtos) {
            entities.add(mapBrandDtoToEntity(dto));
        }
        return entities;
    }

    // Brand Entity List To Brand Domain List
    public static List<Brand> mapBrandEntityListToDomainList(List<BrandEntity> entities) {
        List<Brand> domains = new ArrayList<>();
        for (BrandEntity entity : entities) {
            domains.add(mapBrandEntityToDomain(entity));
        }
        return domains;
    }

    // Latest Gadget DTO to Gadget Domain
    public static Gadget mapLatestGadgetDtoToDomain(GadgetDtoItem dto) {
        Gadget domain = new Gadget();
        domain.setSlug(dto.getSlug());
        domain.setGadgetName(dto.getPhoneName());
        domain.setImage(dto.getImage());
        domain.setSaved(false); // default
        domain.setBrand(""); // brand not included in latest Gadgets
        domain.setReleaseInfo("");
        domain.setOsInfo("");
        return domain;
    }

    // Brand Gadget DTO List to Brand Domain List
    public static List<Gadget> mapBrandGadgetDtoListToDomainList(List<BrandGadgetsDtoItem> dtos) {
        List<Gadget> domains = new ArrayList<>();
        for (BrandGadgetsDtoItem dto : dtos) {
            domains.add(mapBrandGadgetsDtoToDomain(dto));
        }
        return domains;
    }

    // Brand Gadgets DTO To Gadget Domain
    public static Gadget mapBrandGadgetsDtoToDomain(BrandGadgetsDtoItem dto) {
        Gadget gadget = new Gadget();
        gadget.setSlug(dto.getSlug());
        gadget.setGadgetName(dto.getPhoneName());
        gadget.setImage(dto.getImage());
        gadget.setBrand(dto.getBrand());
        gadget.setSaved(false); // default
        gadget.setOsInfo("");
        gadget.setReleaseInfo("");
        return gadget;
    }

    // Gadget Entity To Gadget Domain
    public static Gadget mapGadgetEntityToDomain(GadgetEntity entity) {
        Gadget domain = new Gadget();
        domain.setSlug(entity.getSlug());
        domain.setGadgetName(entity.getGadgetName());
        domain.setImage(entity.getImage());
        domain.setBrand(entity.getBrand());
        domain.setSaved(entity.isSaved());
        domain.setReleaseInfo(entity.getReleaseInfo());
        domain.setOsInfo(entity.getOsInfo());
        return domain;
    }

    // Gadget Domain To Gadget Entity
    public static GadgetEntity mapGadgetDomainToEntity(Gadget domain) {
        GadgetEntity entity = new GadgetEntity();
        entity.setSlug(domain.getSlug());
        entity.setGadgetName(domain.getGadgetName());
        entity.setImage(domain.getImage());
        entity.setBrand(domain.getBrand());
        entity.setSaved(domain.isSaved());
        entity.setReleaseInfo(domain.getReleaseInfo());
        entity.setOsInfo(domain.getOsInfo());
        return entity;
    }

    // Gadget Entity List To Gadget Domain List
    public static List<Gadget> mapGadgetEntityListToDomainList(List<GadgetEntity> entities) {
        List<Gadget> list = new ArrayList<>();
        for (GadgetEntity entity : entities) {
            list.add(mapGadgetEntityToDomain(entity));
        }
        return list;
    }

    // Latest Gadget DTO To Gadget Entity List
    public static List<Gadget> mapLatestGadgetDtoListToDomainList(List<GadgetDtoItem> dtos) {
        List<Gadget> list = new ArrayList<>();
        for (GadgetDtoItem dto : dtos) {
            list.add(mapLatestGadgetDtoToDomain(dto));
        }
        return list;
    }
}
