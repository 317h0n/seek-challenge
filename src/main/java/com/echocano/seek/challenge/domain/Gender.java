package com.echocano.seek.challenge.domain;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * Gender
 * <p>
 * Gender class.
 * <p>
 *
 * @author echocano
 * @since 10/1/24
 */
@Getter
public enum Gender {
    MALE("M", "Male"), FEMALE("F", "Female");

    private String code, description;

    private Gender(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Gender of(String code) {
        return Stream.of(Gender.values())
                .filter(g -> g.code.equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
