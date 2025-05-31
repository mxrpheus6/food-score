package com.mxrpheus.productservice.service.impl;

import com.mxrpheus.productservice.constants.BrandExceptionMessageKeys;
import com.mxrpheus.productservice.constants.TestDataConstants;
import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.dto.response.PageResponse;
import com.mxrpheus.productservice.exception.brand.BrandAlreadyExistsException;
import com.mxrpheus.productservice.exception.brand.BrandNotFoundException;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
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

        assertThat(actualResponse).isEqualTo(expected);
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

        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandMapper).toResponse(brand);
    }

    @Test
    public void getBrandById_ThrowsException_BrandNotFound() {
        Long brandId = TestDataConstants.BRAND_ID;
        String messageKey = BrandExceptionMessageKeys.BRAND_NOT_FOUND_MESSAGE_KEY;
        Object[] args = new Object[]{brandId};

        when(brandServiceValidation.getBrandByIdWithChecks(brandId)).thenThrow(new BrandNotFoundException(messageKey, args));

        assertThatThrownBy(() -> brandService.getBrandById(brandId))
                .isInstanceOf(BrandNotFoundException.class)
                .extracting("messageKey", "args")
                .containsExactly(messageKey, args);

        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandMapper, never()).toResponse(any(Brand.class));
    }

    @Test
    public void createBrand_ReturnsBrandResponse_ValidInputArguments() {
        BrandRequest brandRequest = TestDataConstants.BRAND_REQUEST;
        Brand brand = TestDataConstants.BRAND;
        BrandResponse expectedResponse = TestDataConstants.BRAND_RESPONSE;

        doNothing().when(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());

        when(brandMapper.toEntity(brandRequest)).thenReturn(brand);
        when(brandRepository.save(brand)).thenReturn(brand);
        when(brandMapper.toResponse(brand)).thenReturn(expectedResponse);

        BrandResponse actualResponse = brandService.createBrand(brandRequest);

        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());
        verify(brandMapper).toEntity(brandRequest);
        verify(brandRepository).save(brand);
        verify(brandMapper).toResponse(brand);
    }

    @Test
    public void createBrand_ThrowsException_BrandAlreadyExists() {
        BrandRequest brandRequest = TestDataConstants.BRAND_REQUEST;
        String messageKey = BrandExceptionMessageKeys.BRAND_ALREADY_EXISTS_MESSAGE_KEY;
        Object[] args = new Object[]{brandRequest.name()};

        doThrow(new BrandAlreadyExistsException(messageKey, args))
                .when(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());

        assertThatThrownBy(() -> brandService.createBrand(brandRequest))
                .isInstanceOf(BrandAlreadyExistsException.class)
                .extracting("messageKey", "args")
                .containsExactly(messageKey, args);

        verify(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());
        verify(brandRepository, never()).save(any(Brand.class));
    }

    @Test
    public void updateBrandById_ReturnsBrandResponse_ValidInputArguments() {
        Long brandId = TestDataConstants.BRAND_ID;
        BrandRequest brandRequest = TestDataConstants.BRAND_REQUEST;
        Brand brand = TestDataConstants.BRAND;
        BrandResponse expectedResponse = TestDataConstants.BRAND_RESPONSE;

        when(brandServiceValidation.getBrandByIdWithChecks(brandId)).thenReturn(brand);
        doNothing().when(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());
        doNothing().when(brandMapper).updateBrandFromDto(brandRequest, brand);
        when(brandRepository.save(brand)).thenReturn(brand);
        when(brandMapper.toResponse(brand)).thenReturn(expectedResponse);

        BrandResponse actualResponse = brandService.updateBrandById(brandId, brandRequest);

        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());
        verify(brandMapper).updateBrandFromDto(brandRequest, brand);
        verify(brandRepository).save(brand);
        verify(brandMapper).toResponse(brand);
    }

    @Test
    public void updateBrandById_ThrowsException_BrandNotFound() {
        Long brandId = TestDataConstants.BRAND_ID;
        BrandRequest brandRequest = TestDataConstants.BRAND_REQUEST;
        String messageKey = BrandExceptionMessageKeys.BRAND_NOT_FOUND_MESSAGE_KEY;
        Object[] args = new Object[]{brandId};

        when(brandServiceValidation.getBrandByIdWithChecks(brandId))
                .thenThrow(new BrandNotFoundException(messageKey, args));

        assertThatThrownBy(() -> brandService.updateBrandById(brandId, brandRequest))
                .isInstanceOf(BrandNotFoundException.class)
                .extracting("messageKey", "args")
                .containsExactly(messageKey, args);

        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandRepository, never()).save(any(Brand.class));
    }

    @Test
    public void updateBrandById_ThrowsException_BrandAlreadyExists() {
        Long brandId = TestDataConstants.BRAND_ID;
        BrandRequest brandRequest = TestDataConstants.BRAND_REQUEST;
        Brand brand = TestDataConstants.BRAND;
        String messageKey = BrandExceptionMessageKeys.BRAND_ALREADY_EXISTS_MESSAGE_KEY;
        Object[] args = new Object[] {brandRequest.name()};

        when(brandServiceValidation.getBrandByIdWithChecks(brandId))
                .thenReturn(brand);

        doThrow(new BrandAlreadyExistsException(messageKey, args))
                .when(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());

        assertThatThrownBy(() -> brandService.updateBrandById(brandId, brandRequest))
                .isInstanceOf(BrandAlreadyExistsException.class)
                .extracting("messageKey", "args")
                .containsExactly(messageKey, args);

        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandServiceValidation).checkBrandAlreadyExists(brandRequest.name());
        verify(brandRepository, never()).save(any(Brand.class));
    }

    @Test
    public void deleteBrandById_DeletesBrand_ValidInputArguments() {
        Long brandId = TestDataConstants.BRAND_ID;
        Brand brand = TestDataConstants.BRAND;

        when(brandServiceValidation.getBrandByIdWithChecks(brandId)).thenReturn(brand);
        doNothing().when(brandRepository).delete(brand);

        brandService.deleteBrandById(brandId);

        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandRepository).delete(brand);
    }

    @Test
    public void deleteBrandById_ThrowsException_BrandNotFound() {
        Long brandId = TestDataConstants.BRAND_ID;
        String messageKey = BrandExceptionMessageKeys.BRAND_NOT_FOUND_MESSAGE_KEY;
        Object[] args = new Object[]{brandId};

        when(brandServiceValidation.getBrandByIdWithChecks(brandId))
                .thenThrow(new BrandNotFoundException(messageKey, args));

        assertThatThrownBy(() -> brandService.deleteBrandById(brandId))
                .isInstanceOf(BrandNotFoundException.class)
                .extracting("messageKey", "args")
                .containsExactly(messageKey, args);

        verify(brandServiceValidation).getBrandByIdWithChecks(brandId);
        verify(brandRepository, never()).delete(any(Brand.class));
    }

}
