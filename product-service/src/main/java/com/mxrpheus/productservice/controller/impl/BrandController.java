package com.mxrpheus.productservice.controller.impl;

import com.mxrpheus.productservice.controller.BrandOperations;
import com.mxrpheus.productservice.dto.Marker;
import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.dto.response.PageResponse;
import com.mxrpheus.productservice.service.BrandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
@Validated
public class BrandController implements BrandOperations {

    private final BrandService brandService;

    @Override
    @GetMapping
    public PageResponse<BrandResponse> getAllBrands(@RequestParam(defaultValue = "0") @Min(0) Integer offset,
                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return brandService.getAllBrands(offset, limit);
    }

    @Override
    @GetMapping("/{brandId}")
    public BrandResponse getBrandById(@PathVariable Long brandId) {
        return brandService.getBrandById(brandId);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(Marker.OnCreate.class)
    public BrandResponse createBrand(@RequestBody @Valid BrandRequest brandRequest) {
        return brandService.createBrand(brandRequest);
    }

    @Override
    @PatchMapping("/{brandId}")
    @Validated(Marker.OnUpdate.class)
    public BrandResponse updateBrandById(@PathVariable Long brandId,
                                         @RequestBody @Valid BrandRequest brandRequest) {
        return brandService.updateBrandById(brandId, brandRequest);
    }

    @Override
    @DeleteMapping("/{brandId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrandById(@PathVariable Long brandId) {
        brandService.deleteBrandById(brandId);
    }

}
