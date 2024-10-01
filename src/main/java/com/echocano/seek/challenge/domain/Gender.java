package com.echocano.seek.challenge.domain;

import lombok.Getter;

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
}
