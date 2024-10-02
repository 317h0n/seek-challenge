package com.echocano.seek.challenge.application.dtos;

import com.echocano.seek.challenge.application.enums.ProcessResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * ValidationResult
 * <p>
 * ValidationResult class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {

    private ProcessResult processResult;
    private List<String> errors;
}
