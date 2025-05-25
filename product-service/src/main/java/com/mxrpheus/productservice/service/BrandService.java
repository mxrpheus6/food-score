package com.mxrpheus.productservice.service;

import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.dto.response.PageResponse;

public interface BrandService {

    PageResponse<BrandResponse> getAllBrands(Integer offset, Integer limit);

    BrandResponse getBrandById(Long brandId);

    BrandResponse createBrand(BrandRequest brandRequest);

    BrandResponse updateBrand(Long brandId, BrandRequest brandRequest);

    void deleteBrandById(Long brandId);

}
