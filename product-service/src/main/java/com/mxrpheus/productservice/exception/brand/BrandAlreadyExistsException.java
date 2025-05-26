package com.mxrpheus.productservice.exception.brand;

import com.mxrpheus.productservice.exception.MessageSourceException;

public class BrandAlreadyExistsException extends MessageSourceException {

    public BrandAlreadyExistsException(String message, Object... args) {
        super(message, args);
    }

}
