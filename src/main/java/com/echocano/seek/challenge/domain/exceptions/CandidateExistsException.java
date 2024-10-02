package com.echocano.seek.challenge.domain.exceptions;

/**
 * CandidateNotFoundException
 * <p>
 * CandidateNotFoundException class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */
public class CandidateExistsException extends RuntimeException {

    public CandidateExistsException(String email) {
        super(String.format("There is a candidate with EMAIL %s", email));
    }
}
