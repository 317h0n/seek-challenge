package com.echocano.seek.challenge.infrastructure.spring.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * SwaggerConfiguration
 * <p>
 * SwaggerConfiguration class.
 * <p>
 * @author echocano
 * @since 10/2/24
 */

@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerBearerConfiguration {
}
