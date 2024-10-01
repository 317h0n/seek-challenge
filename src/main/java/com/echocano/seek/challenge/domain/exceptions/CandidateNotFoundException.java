package com.echocano.seek.challenge.domain.exceptions;

/**
 * CandidateNotFoundException
 * <p>
 * CandidateNotFoundException class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */
public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(String uuid) {
        super(String.format("Could not find candidate with UUID %s", uuid));
    }

    public CandidateNotFoundException(Long id) {
        super(String.format("Could not find candidate with ID %s", id));
    }
}
