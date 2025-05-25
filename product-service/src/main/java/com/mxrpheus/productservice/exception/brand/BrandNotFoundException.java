package com.mxrpheus.productservice.exception.brand;

import com.mxrpheus.productservice.exception.MessageSourceException;

public class BrandNotFoundException extends MessageSourceException {

    public BrandNotFoundException(String message, Object... args) {
        super(message, args);
    }

}
