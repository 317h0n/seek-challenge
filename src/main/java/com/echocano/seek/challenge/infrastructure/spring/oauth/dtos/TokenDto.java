package com.echocano.seek.challenge.infrastructure.spring.oauth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * Token class from <a href="https://github.com/danvega/jwt/blob/master/src/main/java/dev/danvega/jwt/Application.java">this link</a>
 * <p>
 * @author danvega
 * @author echocano
 * @since 10/2/24
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Token", description = "Token's information")
public class TokenDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7339666874966394639L;

    @Schema(name = "token", description = "Generated token", example = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoicmVhZC11c2VyIiwiZXhwIjoxNzI3OTIyMTI2LCJpYXQiOjE3Mjc5MTg1MjYsInNjb3BlIjoicmVhZCJ9.FNS8UH_6OJQfoNvOcpFuKCi77W3UcFsHufvvc9adT6zT4q-_-TFcYAO8516O0Ibv4nxPTStL06cVrz5xNQG839HLRlHPtQyVg0GIZwaBRDOwS_ccb7zQEmfNrCv-1hN8V2-XkQvGtR-LNzqy0T-Ewi_AHg81fXovtWuoyjMS5COuPDcttTc9NzYgNR447hBemi1jVPR_nI0UMP3oRAb7nhH1rp3OfJiso8QfZklJ3n5ApYymWpy14ktoPt3dVqcD3wWnJQ9DgCpwpe4D9NFoeV59gS_Gm_kDrCAWiun_xM8c_aEISRSatrSmIRCgSfcO16JTQ2YwoXDKPb8gl_4WhA")
    private String token;

    @Schema(name = "issued_at", description = "Token time creation", example = "2024-10-03T01:22:06.928207133Z")
    @JsonProperty("issued_at")
    private Instant issuedAt;

    @Schema(name = "expires_at", description = "Token time expiration", example = "2024-10-03T02:22:06.928207133Z")
    @JsonProperty("expires_at")
    private Instant expiresAt;
}
