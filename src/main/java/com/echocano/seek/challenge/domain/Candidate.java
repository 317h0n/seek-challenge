package com.echocano.seek.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
public class Candidate implements Serializable {

    private static final long serialVersionUID = 8338004597506036764L;

    private Long id;
    private String uuid;
    private String name;
    private String email;
    private Gender gender;
    private BigDecimal expectedSalary;

}
