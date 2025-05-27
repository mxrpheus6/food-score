package com.mxrpheus.productservice.service.component.validation.impl;

import com.mxrpheus.productservice.constants.BrandExceptionMessageKeys;
import com.mxrpheus.productservice.exception.brand.BrandAlreadyExistsException;
import com.mxrpheus.productservice.exception.brand.BrandNotFoundException;
import com.mxrpheus.productservice.model.Brand;
import com.mxrpheus.productservice.repository.BrandRepository;
import com.mxrpheus.productservice.service.component.validation.BrandServiceValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandServiceValidationImpl implements BrandServiceValidation {

    private final BrandRepository brandRepository;

    @Override
    public Brand getBrandByIdWithChecks(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(
                        BrandExceptionMessageKeys.BRAND_NOT_FOUND_MESSAGE_KEY,
                        brandId
                ));
    }

    @Override
    public void checkBrandAlreadyExists(String brandName) {
        if (brandRepository.existsByName(brandName)) {
            throw new BrandAlreadyExistsException(
                    BrandExceptionMessageKeys.BRAND_ALREADY_EXISTS_MESSAGE_KEY,
                    brandName
            );
        }
    }


}
