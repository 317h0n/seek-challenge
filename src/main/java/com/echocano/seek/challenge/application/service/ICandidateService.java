package com.echocano.seek.challenge.application.service;

import com.echocano.seek.challenge.domain.Candidate;

/**
 * ICandidateService
 * <p>
 * ICandidateService class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

public interface ICandidateService {
    Candidate getCandidate(Long id);
    Candidate getCandidate(String uuid);
    Candidate createCandidate(Candidate candidate);
    Candidate updateCandidate(Candidate candidate);
    void deleteCandidate(String uuid);
}
