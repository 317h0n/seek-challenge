package com.echocano.seek.challenge.application.service.implementation;

import com.echocano.seek.challenge.application.repository.ICandidateRepository;
import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.domain.Gender;
import com.echocano.seek.challenge.domain.exceptions.CandidateExistsException;
import com.echocano.seek.challenge.domain.exceptions.CandidateNotFoundException;
import com.echocano.seek.challenge.domain.exceptions.CandidateParamsException;
import com.echocano.seek.challenge.infrastructure.spring.db.mapper.ICandidateEntityMapperImpl;
import com.echocano.seek.challenge.infrastructure.spring.db.repository.CandidateDboRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * CandidateServiceTest
 * <p>
 * CandidateServiceTest class.
 * <p>
 * @author echocano
 * @since 10/2/24
 */

@ExtendWith(SpringExtension.class)
@Import({
        ICandidateEntityMapperImpl.class,
        CandidateDboRepository.class
})
@DataJpaTest()
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class CandidateServiceIntegrationTest {

    @Autowired
    private ICandidateRepository repository;

    private CandidateService service;

    private Candidate candidate;

    @BeforeEach
    void setUp() {
        service = new CandidateService(repository);
        candidate = Candidate.builder().id(1L).email("uncorreoverdadero@gmail.com").name("Test Uno")
                .expectedSalary(new BigDecimal("3000.00")).gender(Gender.MALE).uuid(UUID.randomUUID().toString())
                .build();
    }

    @Test
    @DisplayName("Should Get Candidates")
    void test1() {
        //Given
        //When
        var result = service.getCandidates();
        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.size());
    }

    @Test
    @DisplayName("Should Get a Candidate")
    void test2() {
        //Given
        String uuid = "b0c8b56e-604f-4d11-8780-ae884735d148";
        //When
        var result = service.getCandidate(uuid);
        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("elthonchocano@gmail.com", result.getEmail());
    }

    @Test()
    @DisplayName("Shouldn't Get a Candidate by uuid")
    void test3() {
        //Given
        String uuid = "123";
        //When
        Exception exception = assertThrows(CandidateNotFoundException.class, () -> {
           service.getCandidate(uuid);
        });
        //Then
        assertEquals("Could not find candidate with UUID 123", exception.getMessage());
    }

    @Test()
    @DisplayName("Should Create a Candidate")
    void test4() {
        //Given
        //When
        var result = service.createCandidate(candidate);
        //Then
        assertEquals(candidate.getName(), result.getName());
        Assertions.assertNotNull(result.getId());
    }

    @Test()
    @DisplayName("Shouldn't Create a Candidate by email already exists")
    void test5() {
        //Given
        candidate.setEmail("elthonchocano@gmail.com");
        //When
        Exception exception = assertThrows(CandidateExistsException.class, () -> {
            service.createCandidate(candidate);
        });
        //Then
        assertEquals("There is a candidate with EMAIL "+candidate.getEmail(), exception.getMessage());
    }

    @Test()
    @DisplayName("Shouldn't Create a Candidate by malformed attribute")
    void test6() {
        //Given
        candidate.setName("Test1");
        //When
        Exception exception = assertThrows(CandidateParamsException.class, () -> {
            service.createCandidate(candidate);
        });
        //Then
        assertEquals("Found 1 params with errors.", exception.getMessage());
    }

    @Test()
    @DisplayName("Should Update a Candidate")
    void test7() {
        //Given
        String name = "Elthon Guillermo Chocano Pareja";
        String uuid = "b0c8b56e-604f-4d11-8780-ae884735d148";
        //When
        candidate = service.getCandidate(uuid);
        candidate.setName(name);
        var result = service.updateCandidate(candidate.getUuid(), candidate);
        //Then
        assertEquals(name, result.getName());
    }

    @Test()
    @DisplayName("Shouldn't Update a Candidate by email already exists")
    void test8() {
        //Given
        String name = "Elthon Guillermo Chocano Pareja";
        String email = "christiancueva@gmail.com";
        String uuid = "b0c8b56e-604f-4d11-8780-ae884735d148";
        candidate = service.getCandidate(uuid);
        candidate.setName(name);
        candidate.setEmail(email);
        //When
        Exception exception = assertThrows(CandidateExistsException.class, () ->
                service.updateCandidate(candidate.getUuid(), candidate)
        );
        //Then
        assertEquals("There is a candidate with EMAIL "+email, exception.getMessage());
    }

    @Test()
    @DisplayName("Should Delete a Candidate")
    void test9() {
        //Given
        String uuid = "b0c8b56e-604f-4d11-8780-ae884735d148";
        service.deleteCandidate(uuid);
        //When
        Exception exception = assertThrows(CandidateNotFoundException.class, () -> {
            service.getCandidate(uuid);
        });
        //Then
        assertEquals("Could not find candidate with UUID " + uuid, exception.getMessage());
    }
}