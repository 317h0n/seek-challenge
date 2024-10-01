package com.echocano.seek.challenge.application.service.implementation;

import com.echocano.seek.challenge.application.repository.CandidateRepository;
import com.echocano.seek.challenge.application.service.ICandidateService;
import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.domain.exceptions.CandidateNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * CandidateService
 * <p>
 * CandidateService class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@RequiredArgsConstructor
public class CandidateService implements ICandidateService {

    private final CandidateRepository repository;

    @Override
    public Candidate getCandidate(Long id) {
        Candidate candidate = repository.findById(id);
        if(candidate == null) {
            throw new CandidateNotFoundException(id);
        }
        return candidate;
    }

    @Override
    public Candidate getCandidate(String uuid) {
        Candidate candidate = repository.findByUuid(uuid);
        if(candidate == null) {
            throw new CandidateNotFoundException(uuid);
        }
        return candidate;
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        if(repository.findByEmail(candidate.getEmail()) != null) {
            throw new CandidateNotFoundException(candidate.getEmail());
        }
        return repository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        return repository.save(candidate);
    }

    @Override
    public void deleteCandidate(String uuid) {
        Candidate candidate = getCandidate(uuid);
        repository.delete(candidate);
    }
}
