package com.echocano.seek.challenge.infrastructure.spring.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CandidateDto
 * <p>
 * CandidateDto class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Candidate", description = "Candidate's information")
public class CandidateDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7695542688561403922L;

    @Schema(name = "uuid", description = "Candidate's uuid", example = "00000000-0000-0000-0000-000000000000")
    private String uuid;

    @Schema(name = "name", description = "Candidate's name", example = "Paolo Guerrero")
    private String name;

    @Schema(name = "email", description = "Candidate's email", example = "paologuerrero@gmail.com")
    private String email;

    @Schema(name = "gender", description = "Candidate's gender", example = "M", allowableValues = {"M", "F"})
    private String gender;

    @Schema(name = "expected_salary", description = "Candidate's expected salary", example = "3000.00")
    @JsonProperty("expected_salary")
    private BigDecimal expectedSalary;
}
