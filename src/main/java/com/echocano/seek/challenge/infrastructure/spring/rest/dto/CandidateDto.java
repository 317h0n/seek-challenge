package com.echocano.seek.challenge.infrastructure.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class CandidateDto implements Serializable {

    private static final long serialVersionUID = 7695542688561403922L;

    private String uuid;
    private String name;
    private String email;
    private String gender;
    private BigDecimal expectedSalary;
}
