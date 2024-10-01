package com.echocano.seek.challenge.infrastructure.db.mapper;

import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.infrastructure.db.dbo.CandidateEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ICandidateEntityMapper
 * <p>
 * ICandidateEntityMapper class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Mapper(componentModel = "spring")
public interface ICandidateEntityMapper {

    Candidate toDomain(CandidateEntity candidateEntity);
    CandidateEntity toEntity(Candidate candidate);

    List<Candidate> toDomain(List<CandidateEntity> candidateEntity);
}
