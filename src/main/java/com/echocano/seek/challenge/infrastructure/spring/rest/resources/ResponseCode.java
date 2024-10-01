package com.echocano.seek.challenge.infrastructure.spring.rest.resources;

import lombok.Getter;

/**
 * ResponseCode
 * <p>
 * ResponseCode class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Getter
public enum ResponseCode {
    OK("200.00.00", "Process ok"),
    OK_CREATED("200.00.01", "Resource successfully created"),
    NOT_FOUND("400.00.01", "Resource not found");

    private final String code;
    private final String message;

    private ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
