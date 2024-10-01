package com.echocano.seek.challenge.infrastructure.db.dbo;

import com.echocano.seek.challenge.domain.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CandidateEntity
 * <p>
 * CandidateEntity class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidates")
public class CandidateEntity implements Serializable {

    private static final long serialVersionUID = -8249254517172832722L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, nullable = false)
    private String uuid;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "gender", length = 1, nullable = false)
    private String genderCode;

    @Transient
    private Gender gender;

    @PostLoad
    void fillTransient() {
        if (genderCode != null) {
            this.gender = Gender.of(genderCode);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (gender != null) {
            this.genderCode = gender.getCode();
        }
    }

    @Column(name = "expected_salary", nullable = false)
    private BigDecimal expectedSalary;
}
