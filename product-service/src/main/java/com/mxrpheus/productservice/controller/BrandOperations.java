package com.mxrpheus.productservice.controller;

import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.dto.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(
        name = "Brand controller",
        description = "This controller contains CRUD operations for brand in product service"
)
public interface BrandOperations {

    @Operation(summary = "Retrieve all brands")
    PageResponse<BrandResponse> getAllBrands(@RequestParam(defaultValue = "0") @Min(0) Integer offset,
                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit);

    @Operation(summary = "Get brand by ID")
    BrandResponse getBrandById(@PathVariable Long brandId);

    @Operation(summary = "Create new brand")
    BrandResponse createBrand(@RequestBody BrandRequest brandRequest);

    @Operation(summary = "Update brand")
    BrandResponse updateBrandById(@PathVariable Long brandId, @RequestBody BrandRequest brandRequest);

    @Operation(summary = "Delete brand")
    void deleteBrandById(@PathVariable Long brandId);
}
