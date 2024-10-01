package com.echocano.seek.challenge.domain.exceptions;

/**
 * CandidateNotFoundException
 * <p>
 * CandidateNotFoundException class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */
public class CandidateExistException extends RuntimeException {

    public CandidateExistException(String email) {
        super(String.format("There is a candidate with EMAIL %s", email));
    }
}
