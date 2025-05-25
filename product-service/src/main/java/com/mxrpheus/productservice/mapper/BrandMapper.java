package com.mxrpheus.productservice.mapper;

import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.model.Brand;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BrandMapper {

    BrandResponse toResponse(Brand brand);

    Brand toEntity(BrandRequest brandRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBrandFromDto(BrandRequest brandRequest, @MappingTarget Brand brand);
}
