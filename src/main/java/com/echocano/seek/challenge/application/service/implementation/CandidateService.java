package com.echocano.seek.challenge.application.service.implementation;

import com.echocano.seek.challenge.application.dtos.ValidationResult;
import com.echocano.seek.challenge.application.enums.ProcessResult;
import com.echocano.seek.challenge.application.repository.ICandidateRepository;
import com.echocano.seek.challenge.application.service.ICandidateService;
import com.echocano.seek.challenge.application.validations.ICandidateValidation;
import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.domain.exceptions.CandidateExistsException;
import com.echocano.seek.challenge.domain.exceptions.CandidateNotFoundException;
import com.echocano.seek.challenge.domain.exceptions.CandidateParamsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * CandidateService
 * <p>
 * CandidateService class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@RequiredArgsConstructor
@Service
public class CandidateService implements ICandidateService {

    private final ICandidateRepository repository;

    @Override
    public Candidate getCandidate(String uuid) {
        Candidate candidate = repository.findByUuid(uuid);
        if(candidate == null) {
            throw new CandidateNotFoundException(uuid);
        }
        return candidate;
    }

    @Override
    public List<Candidate> getCandidates() {
        return repository.findAll();
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        if(repository.findByEmail(candidate.getEmail()) != null) {
            throw new CandidateExistsException(candidate.getEmail());
        }
        candidate.setUuid(UUID.randomUUID().toString());
        validate(candidate);
        return repository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(String uuid, Candidate candidate) {
        Candidate savedCandidate = getCandidate(uuid);
        Candidate existingEmailCandidate = repository.findByEmail(candidate.getEmail());
        if(existingEmailCandidate != null && !existingEmailCandidate.getUuid().equals(savedCandidate.getUuid())) {
            throw new CandidateExistsException(candidate.getEmail());
        }
        validate(candidate);
        savedCandidate.setName(candidate.getName());
        savedCandidate.setEmail(candidate.getEmail());
        savedCandidate.setGender(candidate.getGender());
        savedCandidate.setExpectedSalary(candidate.getExpectedSalary());
        return repository.save(savedCandidate);
    }

    @Override
    public void deleteCandidate(String uuid) {
        Candidate candidate = getCandidate(uuid);
        repository.delete(candidate);
    }

    private void validate(Candidate candidate) {
        final ValidationResult commonValidationResult = ICandidateValidation.validate().apply(candidate);
        if (ProcessResult.PROCESS_FAILED.equals(commonValidationResult.getProcessResult())) {
            throw new CandidateParamsException(commonValidationResult.getErrors());
        }
    }
}
