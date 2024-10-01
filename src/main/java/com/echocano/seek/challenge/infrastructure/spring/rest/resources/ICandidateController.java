package com.echocano.seek.challenge.infrastructure.spring.rest.resources;

import com.echocano.seek.challenge.infrastructure.spring.rest.dto.BaseResponse;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.CandidateDto;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;

/**
 * ICandidateController
 * <p>
 * ICandidateController class.
 * <p>
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES DE NOVOPAYMENT Y SE ENCUENTRA PROTEGIDO POR LAS LEYES DE
 * PROPIEDAD INTELECTUAL Y DERECHOS DE AUTOR.
 *
 * @author Novopayment Inc.
 * @author echocano@novopayment.com
 * @since 10/1/24
 */
public interface ICandidateController {

    @Operation(summary = "Get All Candidates", description = "Retrieve all registered candidates")
    @ApiResponse(
            code = 0, message = "")
    ResponseEntity<BaseResponse> doOnGetCandidates();

    @Operation(summary = "Get Candidate", description = "Retrieve a specific candidate by uuid")
    @ApiResponse(
            code = 0, message = "")
    ResponseEntity<BaseResponse> doOnGetCandidate(
            @Parameter(name = "uuid", description = "Candidate's uuid", required = true)
            String uuid);

    @Operation(summary = "Create a Candidate", description = "Create a new candidate")
    @ApiResponse(
            code = 0, message = "")
    ResponseEntity<BaseResponse> doOnCreateCandidate(
            @Parameter(name = "candidate", description = "Candidate's information", required = true)
            CandidateDto candidate);

    @Operation(summary = "Update a Candidate", description = "Update a candidate's information")
    @ApiResponse(
            code = 0, message = "")
    ResponseEntity<BaseResponse> doOnUpdateCandidate(
            @Parameter(name = "uuid", description = "Candidate's uuid", required = true)
            String uuid,
            @Parameter(name = "candidate", description = "Candidate's information", required = true)
            CandidateDto candidate);

    @Operation(summary = "Delete Candidate", description = "Delete a specific candidate by uuid")
    @ApiResponse(
            code = 0, message = "")
    ResponseEntity<BaseResponse> doOnDeleteCandidate(
            @Parameter(name = "uuid", description = "Candidate's uuid", required = true)
            String uuid);
}
