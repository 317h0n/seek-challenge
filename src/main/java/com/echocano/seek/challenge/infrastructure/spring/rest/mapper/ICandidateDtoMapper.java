package com.echocano.seek.challenge.infrastructure.spring.rest.mapper;

import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.domain.Gender;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.CandidateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

/**
 * ICandidateDtoMapper
 * <p>
 * ICandidateDtoMapper class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface ICandidateDtoMapper {

    @Mapping(target = "gender", expression = "java(domain.gender() ? 1L : 0L)")
    CandidateDto toDto(Candidate domain);

    @Mapping(target = "gender", source = "gender", qualifiedByName = "mapGenderToDomain")
    Candidate toDomain(CandidateDto dto);

    @Named("mapGenderToDomain")
    default Gender mapGenderToDomain(String genderCode) {
        return Gender.of(genderCode);
    }

    @Named("mapGenderToDto")
    default String mapGenderToDto(Gender gender) {
        return gender.getCode();
    }
}
