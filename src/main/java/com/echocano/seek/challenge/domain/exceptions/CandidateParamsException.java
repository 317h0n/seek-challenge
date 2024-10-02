package com.echocano.seek.challenge.domain.exceptions;

import lombok.Getter;

import java.util.List;

/**
 * CandidateParamsException
 * <p>
 * CandidateParamsException class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Getter
public class CandidateParamsException extends RuntimeException {

    private final List<String> errors;

    public CandidateParamsException(List<String> errors) {
        super(String.format("Found %s params with errors.", errors.size()));
        this.errors = errors;
    }
}
