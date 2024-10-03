package com.echocano.seek.challenge.application.repository;

import com.echocano.seek.challenge.domain.Candidate;

import java.util.List;

/**
 * CandidateRepository
 * <p>
 * CandidateRepository class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

public interface ICandidateRepository {
    Candidate findById(Long id);
    Candidate findByUuid(String uuid);
    Candidate findByEmail(String email);
    List<Candidate> findAll();
    Candidate save(Candidate candidate);
    void delete(Candidate candidate);
}
