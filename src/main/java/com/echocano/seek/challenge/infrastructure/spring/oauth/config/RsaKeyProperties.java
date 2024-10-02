package com.echocano.seek.challenge.infrastructure.spring.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RsaKeyProperties
 * <p>
 * RsaKeyProperties class.
 * <p>
 * @author echocano
 * @since 10/2/24
 */

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
