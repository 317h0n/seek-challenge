package com.echocano.seek.challenge.application.validations;

import com.echocano.seek.challenge.application.enums.ProcessResult;
import com.echocano.seek.challenge.application.helpers.ValidationHelper;
import com.echocano.seek.challenge.application.dtos.ValidationResult;
import com.echocano.seek.challenge.domain.Candidate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * ICandidateValidation
 * <p>
 * ICandidateValidation class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@FunctionalInterface
public interface ICandidateValidation extends Function<Candidate, ValidationResult> {

    BigDecimal MIN = new BigDecimal("500.00");
    BigDecimal MAX = new BigDecimal("500000.00");
    String NAME_REGEXP = "^(?!\\\\s)(?!.*\\\\s{2,})[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+( [a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*$";
    /**
     * This is a regexp based in <a href="https://howtodoinjava.com/java/regex/java-regex-validate-email-address/">this website</a>.
     */
    String REGEXP_EMAIL = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    static ICandidateValidation validate() {
        return domain -> {
            final ValidationResult validationResult = ValidationResult.builder()
                    .processResult(ProcessResult.PROCESS_SUCCESSFULLY_COMPLETED)
                    .errors(new ArrayList<>())
                    .build();
            ValidationHelper.validateRequiredField("domain object", domain, validationResult);
            if (!validationResult.getErrors().isEmpty()) {
                return validationResult;
            }
            if (domain.getGender() == null) {
                validationResult.getErrors().add(String.format(ValidationHelper.FIELD_IS_NOT_VALID, "gender"));
                validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
            }
            ValidationHelper.validateRequiredField("name", domain.getName(), 1, 255, validationResult, NAME_REGEXP);
            ValidationHelper.validateRequiredField("email", domain.getEmail(), 1, 255, validationResult, REGEXP_EMAIL);
            ValidationHelper.validateRequiredField("expected_salary", domain.getExpectedSalary(), MIN, MAX, validationResult);
            return validationResult;
        };
    }
}
