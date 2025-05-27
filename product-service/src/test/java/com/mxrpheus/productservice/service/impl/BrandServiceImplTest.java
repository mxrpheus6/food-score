package com.mxrpheus.productservice.service.impl;

import com.mxrpheus.productservice.constants.TestDataConstants;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.dto.response.PageResponse;
import com.mxrpheus.productservice.mapper.BrandMapper;
import com.mxrpheus.productservice.mapper.PageResponseMapper;
import com.mxrpheus.productservice.model.Brand;
import com.mxrpheus.productservice.repository.BrandRepository;
import com.mxrpheus.productservice.service.component.validation.BrandServiceValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @Mock
    private PageResponseMapper pageResponseMapper;

    @Mock
    private BrandServiceValidation brandServiceValidation;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void getAllBrands_ReturnsPageBrandResponse_ValidInputArguments() {
        int offset = TestDataConstants.PAGE_OFFSET;
        int limit = TestDataConstants.PAGE_LIMIT;
        PageResponse<BrandResponse> expected = PageResponse.<BrandResponse>builder()
                .addCurrentOffset(offset)
                .addCurrentLimit(limit)
                .addTotalPages(1)
                .addTotalElements(1)
                .addSort("id")
                .addValues(Collections.singletonList(TestDataConstants.BRAND_RESPONSE))
                .build();
        List<Brand> brands = Collections.singletonList(TestDataConstants.BRAND);
        Page<Brand> brandPage = new PageImpl<>(brands);
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(brandPage);
        when(brandMapper.toResponse(any(Brand.class)))
                .thenReturn(TestDataConstants.BRAND_RESPONSE);
        when(pageResponseMapper.toDto(ArgumentMatchers.<Page<BrandResponse>>any()))
                .thenReturn(expected);

        PageResponse<BrandResponse> actualResponse = brandService.getAllBrands(offset, limit);

        assertThat(actualResponse).isSameAs(expected);
        verify(brandMapper).toResponse(TestDataConstants.BRAND);
        verify(pageResponseMapper).toDto(ArgumentMatchers.<Page<BrandResponse>>any());
    }

    @Test
    void getBrandById_ReturnsBrandResponse_ValidInputArguments() {
        Long brandId = TestDataConstants.BRAND_ID;
        Brand brand = TestDataConstants.BRAND;
        BrandResponse expectedResponse = TestDataConstants.BRAND_RESPONSE;

        when(brandServiceValidation.getBrandByIdWithChecks(brandId)).thenReturn(brand);
        when(brandMapper.toResponse(brand)).thenReturn(expectedResponse);

        BrandResponse actualResponse = brandService.getBrandById(brandId);

        assertThat(actualResponse).isSameAs(expectedResponse);
        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandMapper).toResponse(brand);
    }
}
