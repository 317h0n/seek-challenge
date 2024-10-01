package com.echocano.seek.challenge.infrastructure.db.repository;

import com.echocano.seek.challenge.infrastructure.db.dbo.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * SpringDataCandidateRepository
 * <p>
 * SpringDataCandidateRepository class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Repository
public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, Long> {

    Optional<CandidateEntity> findByEmail(String email);
    Optional<CandidateEntity> findByUuid(String uuid);
}
