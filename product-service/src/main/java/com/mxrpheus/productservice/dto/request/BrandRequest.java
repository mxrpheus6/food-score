package com.mxrpheus.productservice.dto.request;

import com.mxrpheus.productservice.dto.Marker;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Data Transfer Object for Brand")
public record BrandRequest(

        @NotBlank(groups = { Marker.OnCreate.class }, message = "{brand_name.blank.message}")
        @Schema(description = "Brand's name", example = "Coca-Cola")
        String name,

        @NotBlank(groups = { Marker.OnCreate.class }, message = "{brand_website.blank.message}")
        @Schema(description = "Brand's website", example = "https://www.coca-cola.com/")
        String website

) {}
