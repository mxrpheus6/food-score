package com.mxrpheus.productservice.controller.impl;

import com.mxrpheus.productservice.controller.BrandOperations;
import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.dto.response.PageResponse;
import com.mxrpheus.productservice.service.BrandService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
@Validated
public class BrandController implements BrandOperations {

    private final BrandService brandService;

    @GetMapping
    @Override
    public PageResponse<BrandResponse> getAllBrands(@RequestParam(defaultValue = "0") @Min(0) Integer offset,
                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return brandService.getAllBrands(offset, limit);
    }

    @GetMapping("/{brandId}")
    @Override
    public BrandResponse getBrandById(@PathVariable Long brandId) {
        return brandService.getBrandById(brandId);
    }

    @PostMapping
    @Override
    public BrandResponse createBrand(@RequestBody BrandRequest brandRequest) {
        return brandService.createBrand(brandRequest);
    }

    @PutMapping("/{brandId}")
    @Override
    public BrandResponse updateBrandById(@PathVariable Long brandId,
                                         @RequestBody BrandRequest brandRequest) {
        return brandService.updateBrand(brandId, brandRequest);
    }

    @DeleteMapping("/{brandId}")
    @Override
    public void deleteBrandById(@PathVariable Long brandId) {
        brandService.deleteBrandById(brandId);
    }

}
