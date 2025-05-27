package com.mxrpheus.productservice.service.impl;

import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.dto.response.PageResponse;
import com.mxrpheus.productservice.mapper.BrandMapper;
import com.mxrpheus.productservice.mapper.PageResponseMapper;
import com.mxrpheus.productservice.model.Brand;
import com.mxrpheus.productservice.repository.BrandRepository;
import com.mxrpheus.productservice.service.BrandService;
import com.mxrpheus.productservice.service.component.validation.BrandServiceValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final BrandServiceValidation brandServiceValidation;
    private final PageResponseMapper pageResponseMapper;

    @Override
    public PageResponse<BrandResponse> getAllBrands(Integer offset, Integer limit) {
        Page<BrandResponse> brandsPageDto = brandRepository
                .findAll(PageRequest.of(offset, limit))
                .map(brandMapper::toResponse);

        return pageResponseMapper.toDto(brandsPageDto);
    }

    @Override
    public BrandResponse getBrandById(Long brandId) {
        Brand brand = brandServiceValidation.getBrandByIdWithChecks(brandId);

        return brandMapper.toResponse(brand);
    }

    @Override
    @Transactional
    public BrandResponse createBrand(BrandRequest brandRequest) {
        String brandName = brandRequest.name();
        brandServiceValidation.checkBrandAlreadyExists(brandName);

        Brand brand = brandMapper.toEntity(brandRequest);
        brandRepository.save(brand);
        return brandMapper.toResponse(brand);
    }

    @Override
    @Transactional
    public BrandResponse updateBrand(Long brandId, BrandRequest brandRequest) {
        Brand brand = brandServiceValidation.getBrandByIdWithChecks(brandId);

        String brandName = brandRequest.name();
        brandServiceValidation.checkBrandAlreadyExists(brandName);

        brandMapper.updateBrandFromDto(brandRequest, brand);
        brandRepository.save(brand);
        return brandMapper.toResponse(brand);
    }

    @Override
    public void deleteBrandById(Long brandId) {
        Brand brand = brandServiceValidation.getBrandByIdWithChecks(brandId);

        brandRepository.delete(brand);
    }

}
