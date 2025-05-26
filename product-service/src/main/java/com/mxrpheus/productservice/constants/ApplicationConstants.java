package com.mxrpheus.productservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConstants {

    public static final String WEBSITE_URL_REGEX = "^(https?:\\/\\/)?(www\\.)?[\\p{L}0-9-]+(\\.[\\p{L}]{2,})+(?:\\/[^\\s]*)?$";

}
