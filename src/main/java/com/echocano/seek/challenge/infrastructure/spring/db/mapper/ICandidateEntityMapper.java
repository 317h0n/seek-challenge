package com.echocano.seek.challenge.infrastructure.spring.db.mapper;

import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.infrastructure.spring.db.dbo.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * ICandidateEntityMapper
 * <p>
 * ICandidateEntityMapper class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface ICandidateEntityMapper {

    Candidate toDomain(CandidateEntity candidateEntity);
    CandidateEntity toEntity(Candidate candidate);

    List<Candidate> toDomain(List<CandidateEntity> candidateEntity);
}
