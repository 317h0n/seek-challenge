package com.echocano.seek.challenge.infrastructure.spring.db.repository;

import com.echocano.seek.challenge.domain.Candidate;
import com.echocano.seek.challenge.domain.Gender;
import com.echocano.seek.challenge.infrastructure.spring.db.mapper.ICandidateEntityMapper;
import com.echocano.seek.challenge.infrastructure.spring.db.mapper.ICandidateEntityMapperImpl;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * CandidateDboRepositoryTest
 * <p>
 * CandidateDboRepositoryTest class.
 * <p>
 * @author echocano
 * @since 10/3/24
 */

@ExtendWith(SpringExtension.class)
@Import({
        ICandidateEntityMapperImpl.class
})
@DataJpaTest()
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class CandidateDboRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JpaCandidateRepository jpaRepository;

    private CandidateDboRepository repository;

    @Autowired
    private ICandidateEntityMapper mapper;

    @Test
    void injectedComponentsAreNotNull() {
        Assertions.assertThat(dataSource).isNotNull();
        Assertions.assertThat(jdbcTemplate).isNotNull();
        Assertions.assertThat(entityManager).isNotNull();
        Assertions.assertThat(jpaRepository).isNotNull();
    }

    @Test
    @DisplayName("Should Candidate exist by uuid")
    void test1() {
        //Given
        String uuid = "b0c8b56e-604f-4d11-8780-ae884735d148";
        repository = new CandidateDboRepository(jpaRepository, mapper);
        //When
        var candidate = repository.findByUuid(uuid);
        //Then
        Assertions.assertThat(candidate).isNotNull();
    }

    @Test
    @DisplayName("Should Candidate exist by id")
    void test2() {
        //Given
        Long id = 1L;
        repository = new CandidateDboRepository(jpaRepository, mapper);
        //When
        var candidate = repository.findById(id);
        //Then
        Assertions.assertThat(candidate).isNotNull();
    }

    @Test
    @DisplayName("Should Candidate exist by email")
    void test3() {
        //Given
        String email = "elthonchocano@gmail.com";
        repository = new CandidateDboRepository(jpaRepository, mapper);
        //When
        var candidate = repository.findByEmail(email);
        //Then
        Assertions.assertThat(candidate).isNotNull();
    }

    @Test
    @DisplayName("Should Candidates exist")
    void test4() {
        //Given
        repository = new CandidateDboRepository(jpaRepository, mapper);
        //When
        var candidates = repository.findAll();
        //Then
        Assertions.assertThat(candidates).hasSize(5);
    }

    @Test
    @DisplayName("Should Create a Candidate")
    void test5() {
        //Given
        Candidate candidate = Candidate.builder()
                .uuid(UUID.randomUUID().toString())
                .name("Jeferson Farfan")
                .email("jefersonfarfan@gmail.com")
                .gender(Gender.MALE)
                .expectedSalary(new BigDecimal(3000))
                .build();
        repository = new CandidateDboRepository(jpaRepository, mapper);
        //When
        var saved = repository.save(candidate);
        //Then
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(5L);
    }

    @Test
    @DisplayName("Should Delete a Candidate")
    void test6() {
        //Given
        String uuid = "b0c8b56e-604f-4d11-8780-ae884735d148";
        repository = new CandidateDboRepository(jpaRepository, mapper);
        //When
        var candidate = repository.findByUuid(uuid);
        Assertions.assertThat(candidate).isNotNull();
        repository.delete(candidate);
        candidate = repository.findByUuid(uuid);
        //Then
        Assertions.assertThat(candidate).isNull();
    }
}