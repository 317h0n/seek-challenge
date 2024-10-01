package com.echocano.seek.challenge.infrastructure.db.repository;

import com.echocano.seek.challenge.application.repository.CandidateRepository;
import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.infrastructure.db.mapper.ICandidateEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CandidateDboRepository
 * <p>
 * CandidateDboRepository class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Log4j2
@RequiredArgsConstructor
@Repository
public class CandidateDboRepository implements CandidateRepository {

    private final JpaCandidateRepository repository;
    private final ICandidateEntityMapper mapper;

    @Override
    public Candidate findById(Long id) {
        return mapper.toDomain(repository.findById(id).orElse(null));
    }

    @Override
    public Candidate findByUuid(String uuid) {
        return mapper.toDomain(repository.findByUuid(uuid).orElse(null));
    }

    @Override
    public Candidate findByEmail(String email) {
        return mapper.toDomain(repository.findByEmail(email).orElse(null));
    }

    @Override
    public List<Candidate> findAll() {
        return mapper.toDomain(repository.findAll());
    }

    @Override
    public Candidate save(Candidate candidate) {
        return mapper.toDomain(repository.save(mapper.toEntity(candidate)));
    }

    @Override
    public void delete(Candidate candidate) {
        repository.delete(mapper.toEntity(candidate));
    }
}
