package com.mxrpheus.productservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Product Service API",
                description = "Service provides API for actions connected to products.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Pavel Kazachenko",
                        email = "pkazachenkoo@icloud.com",
                        url = "https://www.linkedin.com/in/pavel-kazachenko/"
                )
        )
)
@Configuration
public class SwaggerConfig {
}
