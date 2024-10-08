package com.echocano.seek.challenge.infrastructure.spring.rest.dto;

import com.echocano.seek.challenge.application.helpers.DateHelper;
import com.echocano.seek.challenge.infrastructure.spring.rest.resources.enums.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * BaseResponse
 * <p>
 * BaseResponse class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BaseResponse", description = "Base resource payload")
public class BaseResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -3295335366828440909L;

    @Schema(name = "code", description = "Response code", example = "200.00.000")
    private String code;

    @Schema(name = "datetime", description = "Response datetime", example = "2024-02-01T02:31:33.666Z")
    private String datetime = DateHelper.getStringUTCNow();

    @Schema(name = "message", description = "Response message", example = "Process ok.")
    private String message;

    @Schema(name = "data", description = "Response data")
    private transient T data;

    public static <T extends Serializable> BaseResponse<T> buildResponse(ResponseCode code, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        response.setDatetime(DateHelper.getStringUTCNow());
        response.setData(data);
        return response;
    }
}
