package com.mxrpheus.productservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for Brand")
public record BrandResponse(

        Long id,
        String name,
        String website

) {}
