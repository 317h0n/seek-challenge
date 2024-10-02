package com.echocano.seek.challenge.infrastructure.spring.oauth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * Token
 * <p>
 * Token class.
 * <p>
 * @author echocano
 * @since 10/2/24
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7339666874966394639L;

    private String token;

    @JsonProperty("issued_at")
    private Instant issuedAt;

    @JsonProperty("expires_at")
    private Instant expiresAt;
}
