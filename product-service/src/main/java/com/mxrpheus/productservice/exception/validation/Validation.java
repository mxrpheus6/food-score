package com.mxrpheus.productservice.exception.validation;

public record Validation(

        String fieldName,
        String message

) {
}
