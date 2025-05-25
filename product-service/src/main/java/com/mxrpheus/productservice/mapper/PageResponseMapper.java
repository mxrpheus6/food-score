package com.mxrpheus.productservice.mapper;

import com.mxrpheus.productservice.dto.response.PageResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface PageResponseMapper {

    default <T> PageResponse<T> toDto(Page<T> page) {
        Pageable pageable = page.getPageable();

        return PageResponse.<T>builder()
                .addValues(page.getContent())
                .addTotalElements(page.getTotalElements())
                .addCurrentOffset(pageable.getPageNumber())
                .addCurrentLimit(pageable.getPageSize())
                .addTotalPages(page.getTotalPages())
                .addSort(page.getSort().toString())
                .build();
    }

}
