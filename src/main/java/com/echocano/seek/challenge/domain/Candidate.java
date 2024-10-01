package com.echocano.seek.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Candidate
 * <p>
 * Candidate class.
 * <p>
 *
 * @author echocano
 * @since 10/1/24
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    private String name;
    private String email;
    private Gender gender;
    private BigDecimal expectedSalary;

}
