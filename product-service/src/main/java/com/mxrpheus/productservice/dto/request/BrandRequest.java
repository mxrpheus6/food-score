package com.mxrpheus.productservice.dto.request;

import com.mxrpheus.productservice.constants.ApplicationConstants;
import com.mxrpheus.productservice.dto.Marker;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Data Transfer Object for Brand")
public record BrandRequest(

        @NotBlank(groups = { Marker.OnCreate.class }, message = "{brand_name.blank.message}")
        @Schema(description = "Brand's name", example = "Coca-Cola")
        String name,

        @NotBlank(groups = { Marker.OnCreate.class }, message = "{brand_website.blank.message}")
        @Schema(description = "Brand's website", example = "https://www.coca-cola.com/")
        @Pattern(groups = { Marker.OnCreate.class, Marker.OnUpdate.class },
                regexp = ApplicationConstants.WEBSITE_URL_REGEX,
                message = "{brand_website.regexp.message}")
        String website

) {}
