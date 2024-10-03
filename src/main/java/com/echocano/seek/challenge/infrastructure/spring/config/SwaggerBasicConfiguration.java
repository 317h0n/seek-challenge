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
        name = "BasicAuthorization",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class SwaggerBasicConfiguration {
}
