package com.echocano.seek.challenge.infrastructure.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Error
 * <p>
 * Error class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -1858870333245605317L;

    private List<String> errors;

    public void addErrorMessage(String errorMessage) {
        if(this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorMessage);
    }
}
