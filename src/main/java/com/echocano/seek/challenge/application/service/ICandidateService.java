package com.echocano.seek.challenge.application.service;

import com.echocano.seek.challenge.domain.Candidate;

import java.util.List;

/**
 * ICandidateService
 * <p>
 * ICandidateService class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

public interface ICandidateService {

    List<Candidate> getCandidates();
    Candidate getCandidate(String uuid);
    Candidate createCandidate(Candidate candidate);
    Candidate updateCandidate(String uuid, Candidate candidate);
    void deleteCandidate(String uuid);

}
