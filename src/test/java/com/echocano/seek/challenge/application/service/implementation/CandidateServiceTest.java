package com.echocano.seek.challenge.application.service.implementation;

import com.echocano.seek.challenge.application.repository.ICandidateRepository;
import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.domain.Gender;
import com.echocano.seek.challenge.domain.exceptions.CandidateExistsException;
import com.echocano.seek.challenge.domain.exceptions.CandidateNotFoundException;
import com.echocano.seek.challenge.domain.exceptions.CandidateParamsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CandidateServiceTest
 * <p>
 * CandidateServiceTest class.
 * <p>
 * @author echocano
 * @since 10/2/24
 */

@ExtendWith(SpringExtension.class)
class CandidateServiceTest {

    @Mock
    private ICandidateRepository repository;

    @InjectMocks
    private CandidateService service;

    private List<Candidate> candidates;
    private Candidate candidate, candidate2;

    @BeforeEach
    void setUp() {
        candidate = Candidate.builder().id(1L).email("uncorreoverdadero@gmail.com").name("Test Uno")
                .expectedSalary(new BigDecimal("3000.00")).gender(Gender.MALE).uuid(UUID.randomUUID().toString())
                .build();
        candidate2 = Candidate.builder().id(2L).email("email2@email.com").name("Test2")
                .expectedSalary(new BigDecimal("4000.00")).gender(Gender.FEMALE).uuid(UUID.randomUUID().toString())
                .build();
        candidates = List.of(candidate, candidate2);
    }

    @Test
    @DisplayName("Should Get Candidates")
    void test1() {
        Mockito.when(repository.findAll()).thenReturn(candidates);
        var result = service.getCandidates();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(candidates.size(), result.size());
    }

    @Test
    @DisplayName("Should Get a Candidate")
    void test2() {
        Mockito.when(repository.findByUuid(Mockito.anyString())).thenReturn(candidate);
        var result = service.getCandidate(candidate.getUuid());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(candidate.getEmail(), result.getEmail());
    }

    @Test()
    @DisplayName("Shouldn't Get a Candidate by uuid")
    void test3() {
        String uuid = "123";
        Mockito.when(repository.findByUuid(uuid)).thenReturn(null);
        Exception exception = assertThrows(CandidateNotFoundException.class, () -> {
           service.getCandidate(uuid);
        });
        assertEquals("Could not find candidate with UUID 123", exception.getMessage());
    }

    @Test()
    @DisplayName("Should Create a Candidate")
    void test4() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.save(candidate)).thenReturn(candidate);
        var result = service.createCandidate(candidate);
        assertEquals(candidate.getName(), result.getName());
    }

    @Test()
    @DisplayName("Shouldn't Create a Candidate by email already exists")
    void test5() {
        Mockito.when(repository.findByEmail(Mockito.anyString()))
                .thenReturn(Candidate.builder().email(candidate.getEmail()).build());
        Mockito.when(repository.save(candidate)).thenReturn(candidate);
        Exception exception = assertThrows(CandidateExistsException.class, () -> {
            service.createCandidate(candidate);
        });
        assertEquals("There is a candidate with EMAIL "+candidate.getEmail(), exception.getMessage());
    }

    @Test()
    @DisplayName("Shouldn't Create a Candidate by malformed attribute")
    void test6() {
        candidate.setName("Test1");
        Mockito.when(repository.findByEmail(Mockito.anyString()))
                .thenReturn(null);
        Mockito.when(repository.save(candidate)).thenReturn(candidate);
        Exception exception = assertThrows(CandidateParamsException.class, () -> {
            service.createCandidate(candidate);
        });
        assertEquals("Found 1 params with errors.", exception.getMessage());
    }

    @Test()
    @DisplayName("Should Update a Candidate")
    void test7() {
        Mockito.when(repository.findByUuid(candidate.getUuid())).thenReturn(candidate);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.save(candidate)).thenReturn(candidate);
        var result = service.updateCandidate(candidate.getUuid(), candidate);
        assertEquals(candidate.getName(), result.getName());
    }

    @Test()
    @DisplayName("Shouldn't Update a Candidate by email already exists")
    void test8() {
        candidate.setEmail(candidate2.getEmail());
        Mockito.when(repository.findByUuid(candidate.getUuid())).thenReturn(candidate);
        Mockito.when(repository.findByEmail(candidate.getEmail())).thenReturn(candidate2);
        Exception exception = assertThrows(CandidateExistsException.class, () ->
                service.updateCandidate(candidate.getUuid(), candidate)
        );
        assertEquals("There is a candidate with EMAIL "+candidate.getEmail(), exception.getMessage());
    }

    @Test()
    @DisplayName("Should Delete a Candidate")
    void test9() {
        Mockito.when(repository.findByUuid(candidate.getUuid())).thenReturn(candidate);
        Mockito.doNothing().when(repository).delete(candidate);
        service.deleteCandidate(candidate.getUuid());
        Mockito.verify(repository, Mockito.times(1)).delete(candidate);
    }
}