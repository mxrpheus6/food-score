package com.mxrpheus.productservice.constants;

import com.mxrpheus.productservice.dto.request.BrandRequest;
import com.mxrpheus.productservice.dto.response.BrandResponse;
import com.mxrpheus.productservice.model.Brand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataConstants {

    public static final Long BRAND_ID = 1L;
    public static final String BRAND_NAME = "Lay's";
    public static final String BRAND_WEBSITE = "https://www.lays.com";
    public static final Brand BRAND;
    public static final int PAGE_OFFSET = 1;
    public static final int PAGE_LIMIT = 10;

    static {
        BRAND = new Brand();
        BRAND.setId(1L);
        BRAND.setName("Coca-Cola");
        BRAND.setWebsite("https://www.coca-cola.com");
    }

    public static final BrandRequest BRAND_REQUEST = new BrandRequest(
            BRAND_NAME,
            BRAND_WEBSITE
    );

    public static final BrandResponse BRAND_RESPONSE = new BrandResponse(
            1L,
            "Twix",
            "https://www.twix.com"
    );

    public static final String GET_ALL_BRANDS_ENDPOINT = "api/v1/brands";
    public static final String GET_BRAND_BY_ID_ENDPOINT = "api/v1/brands/{brandId}";
    public static final String CREATE_BRAND_ENDPOINT = "api/v1/brands";
    public static final String UPDATE_BRAND_BY_ID_ENDPOINT = "api/v1/brands/{brandId}";
    public static final String DELETE_BRAND_BY_ID_ENDPOINT = "api/v1/brands/{brandId}";
    public static final String INVALID_ARGS_GET_ALL_BRANDS_ENDPOINT = "api/v1/brands?offset=-1&limit=52";
    public static final String INVALID_OFFSET_MESSAGE = "must be grater or equal to 0";
    public static final String INVALID_WEBSITE_MESSAGE = "Incorrect brand's URL (example: https://example.com)";
    public static final String HTTP_STATUS_CONFLICT = "CONFLICT";
    public static final String HTTP_STATUS_NOT_FOUND = "NOT_FOUND";

    public static final BrandRequest INVALID_BRAND_REQUEST_DATA = new BrandRequest(
            "KitKat",
            "httpsssss://www.kitkat.com/"
    );

}
