package com.mxrpheus.productservice.service.component.validation;

import com.mxrpheus.productservice.model.Brand;

public interface BrandServiceValidation {

    Brand getBrandByIdWithChecks(Long brandId);

    void checkBrandAlreadyExists(String name);

}
