package com.echocano.seek.challenge.application.helpers;

import com.echocano.seek.challenge.application.dtos.ValidationResult;
import com.echocano.seek.challenge.application.enums.ProcessResult;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * ValidationHelper
 * <p>
 * ValidationHelper class.
 * <p>
 *
 * @author echocano
 * @since 10/1/24
 */

@UtilityClass
public class ValidationHelper {

    public final String FIELD_IS_NOT_VALID = "Field %s is not valid";
    public final String NON_REQUIRED_FIELD_IS_NOT_EMPTY = "Field %s It must not be empty or blank spaces";
    public final String NON_REQUIRED_LIST_IS_NOT_EMPTY = "Field %s It must not be empty";
    public final String REQUIRED_FIELD_IS_NOT_EMPTY = "Field %s is required, it must be not empty or blank";
    public final String FIELD_MAX_TEXT_SIZE = "Field %s exceeded character limit (%s)";
    public final String FIELD_MIN_TEXT_SIZE = "Field %s has not reached the minimum character limit (%s)";
    public final String FIELD_MAX_SIZE = "Field %s exceeded limit (%s)";
    public final String FIELD_MIN_SIZE = "Field %s has not reached the minimum limit (%s)";

    public static String buildFieldIsNotValidMessage(String field) {
        return String.format(FIELD_IS_NOT_VALID, field);
    }

    public static void validateRequiredField(String field, List<?> value, ValidationResult validationResult) {
        if (incompleteValue(value)) {
            validationResult.getErrors().add(String.format(REQUIRED_FIELD_IS_NOT_EMPTY, field));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateNonRequiredField(String field, List<?> value, ValidationResult validationResult) {
        if (Objects.nonNull(value) && value.isEmpty()) {
            validationResult.getErrors().add(String.format(NON_REQUIRED_LIST_IS_NOT_EMPTY, field));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateRequiredField(String field, Object value, ValidationResult validationResult) {
        if (Objects.isNull(value)) {
            validationResult.getErrors().add(String.format(REQUIRED_FIELD_IS_NOT_EMPTY, field));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateRequiredField(String field, BigDecimal value, BigDecimal min, BigDecimal max, ValidationResult validationResult) {
        validateField(field, value, min, max, validationResult, true);
    }

    public static void validateNonRequiredField(String field, BigDecimal value, BigDecimal min, BigDecimal max, ValidationResult validationResult) {
        validateField(field, value, min, max, validationResult, false);
    }

    public static void validateRequiredField(String field, int value, int min, int max, ValidationResult validationResult) {
        validateField(field, value, min, max, validationResult);
    }

    public static void validateRequiredField(String field, String value, int min, int max, ValidationResult validationResult) {
        validateRequiredField(field, value, min, max, validationResult, null);
    }

    public static void validateNonRequiredField(String field, String value, int min, int max, ValidationResult validationResult) {
        validateNonRequiredField(field, value, min, max, validationResult, null);
    }

    public static void validateRequiredField(String field, String value, int min, int max, ValidationResult validationResult, String regexp) {
        validateField(field, value, min, max, validationResult, regexp, true);
    }

    public static void validateNonRequiredField(String field, String value, int min, int max, ValidationResult validationResult, String regexp) {
        validateField(field, value, min, max, validationResult, regexp, false);
    }

    private static void validateField(String field, String value, int min, int max, ValidationResult validationResult, String regexp, boolean isRequired) {
        if (isRequired) {
            if (incompleteValue(value)) {
                validationResult.getErrors().add(String.format(REQUIRED_FIELD_IS_NOT_EMPTY, field));
                validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
                return;
            }
        } else {
            if (Objects.nonNull(value) && value.isBlank()) {
                validationResult.getErrors().add(String.format(NON_REQUIRED_FIELD_IS_NOT_EMPTY, field));
                validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
                return;
            }
            if (Objects.isNull(value)) {
                return;
            }
        }
        if (value.length() < min) {
            validationResult.getErrors().add(String.format(FIELD_MIN_TEXT_SIZE, field, min));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
        if (value.length() > max) {
            validationResult.getErrors().add(String.format(FIELD_MAX_TEXT_SIZE, field, max));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
        if (regexp != null && !regexp.isBlank() && !validValue(value, regexp)) {
            validationResult.getErrors().add(String.format(FIELD_IS_NOT_VALID, field));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    private static void validateField(String field, BigDecimal value, BigDecimal min, BigDecimal max, ValidationResult validationResult, boolean isRequired) {
        if (isRequired) {
            if (Objects.isNull(value)) {
                validationResult.getErrors().add(String.format(REQUIRED_FIELD_IS_NOT_EMPTY, field));
                validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
                return;
            }
        } else {
            if (Objects.isNull(value)) {
                validationResult.getErrors().add(String.format(NON_REQUIRED_FIELD_IS_NOT_EMPTY, field));
                validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
                return;
            }
        }
        if (value.compareTo(min) < 0) {
            validationResult.getErrors().add(String.format(FIELD_MIN_SIZE, field, min));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
        if (value.compareTo(max) > 0) {
            validationResult.getErrors().add(String.format(FIELD_MAX_SIZE, field, max));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    private static void validateField(String field, int value, int min, int max, ValidationResult validationResult) {
        if (value < min) {
            validationResult.getErrors().add(String.format(FIELD_MIN_SIZE, field, min));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
        if (value > max) {
            validationResult.getErrors().add(String.format(FIELD_MAX_SIZE, field, max));
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static boolean incompleteValue(final String value) {
        return Objects.isNull(value) || value.trim().isEmpty();
    }

    public static boolean incompleteValue(final Integer value) { return Objects.isNull(value) || value == 0; }

    public static boolean incompleteValue(final Long value) { return Objects.isNull(value) || value == 0; }

    private static boolean incompleteValue(final List<?> value) {
        return Objects.isNull(value) || value.isEmpty();
    }

    public static boolean existValue(final List<?> value) {
        return !incompleteValue(value);
    }

    private static boolean validValue(final String value,final String regex) {
        return value.matches(regex);
    }

    public static void validateNotNull(Object value, String errorMessage, ValidationResult validationResult) {
        if (Objects.isNull(value)) {
            validationResult.getErrors().add(errorMessage);
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateValidValue(String value,String regex, String errorMessage, ValidationResult validationResult) {
        if (!validValue(value,regex)) {
            validationResult.getErrors().add(errorMessage);
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateNotEmpty(String value, String errorMessage, ValidationResult validationResult) {
        if (incompleteValue(value)) {
            validationResult.getErrors().add(errorMessage);
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateNotEmpty(Long value, String errorMessage, ValidationResult validationResult) {
        if (incompleteValue(value)) {
            validationResult.getErrors().add(errorMessage);
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateNotEmpty(List<?> value, String errorMessage, ValidationResult validationResult) {
        if (incompleteValue(value)) {
            validationResult.getErrors().add(errorMessage);
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateIsNull(List<?> value, String errorMessage, ValidationResult validationResult) {
        if (Objects.isNull(value) || value.isEmpty()) {
            validationResult.getErrors().add(errorMessage);
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }

    public static void validateSize(String value, int min, int max, String errorMessage, ValidationResult validationResult) {
        if (Objects.isNull(value) || (value.length() < min || value.length() > max)) {
            validationResult.getErrors().add(errorMessage);
            validationResult.setProcessResult(ProcessResult.PROCESS_FAILED);
        }
    }
}
