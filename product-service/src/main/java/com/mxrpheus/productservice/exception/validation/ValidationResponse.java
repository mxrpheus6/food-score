package com.mxrpheus.productservice.exception.validation;

import java.util.List;

public record ValidationResponse(

        List<Validation> errors

) {
}
