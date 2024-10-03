package com.echocano.seek.challenge.infrastructure.spring.rest.resources;

import com.echocano.seek.challenge.infrastructure.spring.rest.dto.BaseResponse;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.CandidateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * ICandidateController
 * <p>
 * ICandidateController class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Tag(name = "Candidates", description = "Candidates Api")
@SecurityRequirement(name = "Authorization")
public interface ICandidateController {

    String JSON_RESPONSE_MALFORMED_PARAMETERS = """
                                    {
                                         "code": "400.00.03",
                                         "datetime": "2024-10-02T02:02:06.006Z",
                                         "message": "Bad request, some parameters are malformed.",
                                         "data": {
                                             "errors": [
                                                "Field name: is not valid",
                                                "Field email: is not valid",
                                                "Field gender: is not valid",
                                                "Field expected_salary: is not valid"
                                            ]
                                         }
                                     }
                                    """;
    String JSON_RESPONSE_DUPLICATE_EMAIL = """
                                    {
                                         "code": "400.00.02",
                                         "datetime": "2024-10-02T02:02:06.006Z",
                                         "message": "Bad request, email already exists.",
                                         "data": {
                                            "errors": [
                                                "There is a candidate with EMAIL correo@correo.com"
                                            ]
                                         }
                                     }
                                    """;

    String JSON_RESPONSE_NOT_FOUND = """
                                    {
                                        "code": "400.00.01",
                                        "message": "Resource not found.",
                                        "datetime": "2020-01-01T00:00:00.000Z",
                                        "data": {
                                            "errors": [
                                                "Could not find candidate with UUID 00000000-0000-0000-0000-000000000000"
                                            ]
                                        }
                                    }
                                    """;
    String JSON_RESPONSE_GET_SAVE_UPDATE = """
                                    {
                                        "code": "200.00.00",
                                        "message": "Process ok.",
                                        "datetime": "2020-01-01T00:00:00.000Z",
                                        "data": {
                                            "uuid": "b0c8b56e-604f-4d11-8780-ae884735d148",
                                            "name": "Elthon Chocano",
                                            "email": "elthonchocano@gmail.com",
                                            "gender": "M",
                                            "expected_salary": 2500.00
                                        }
                                    }
                                    """;
    String JSON_RESPONSE_GET_ALL = """
                                    {
                                        "code": "200.00.00",
                                        "message": "Process ok.",
                                        "datetime": "2020-01-01T00:00:00.000Z",
                                        "data": [
                                            {
                                                "uuid": "b0c8b56e-604f-4d11-8780-ae884735d148",
                                                "name": "Elthon Chocano",
                                                "email": "elthonchocano@gmail.com",
                                                "gender": "M",
                                                "expected_salary": 2500.00
                                            },
                                            {
                                                "uuid": "7867f334-91b7-4357-8ca2-7fbc50e1b04c",
                                                "name": "Claudio Pizarro",
                                                "email": "claudiopizarro@gmail.com",
                                                "gender": "M",
                                                "expected_salary": 250000.00
                                            }
                                        ]
                                    }
                                    """;

    @Operation(summary = "Get All Candidates", description = "Retrieve all registered candidates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"
                    , content = @Content(mediaType = "application/json"
                    , examples = {
                        @ExampleObject(name = "Success response",
                            description = "Retrieve a list of Candidates.",
                            value = JSON_RESPONSE_GET_ALL)
            }))})
    ResponseEntity<BaseResponse<List<CandidateDto>>> doOnGetCandidates();

    @Operation(summary = "Get Candidate", description = "Retrieve a specific candidate by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"
                    , content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Success response",
                            description = "Retrieve a Candidate.",
                            value = JSON_RESPONSE_GET_SAVE_UPDATE)
            })),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Not Found response",
                            description = "Fail to retrieve a Candidate.",
                            value = JSON_RESPONSE_NOT_FOUND)
            }))
    })
    ResponseEntity<BaseResponse<Serializable>> doOnGetCandidate(
            @Parameter(name = "uuid", description = "Candidate's uuid", required = true)
            String uuid);

    @Operation(summary = "Create a Candidate", description = "Create a new candidate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Success response",
                            description = "Create a new Candidate.",
                            value = JSON_RESPONSE_GET_SAVE_UPDATE)
            })),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json"
                    , examples = {
                        @ExampleObject(name = "Duplicate email response",
                            description = "Fail to create a Candidate.",
                            value = JSON_RESPONSE_DUPLICATE_EMAIL),
                        @ExampleObject(name = "Malformed parameters response",
                            description = "Fail to create a Candidate.",
                            value = JSON_RESPONSE_MALFORMED_PARAMETERS)
                    })
            )
    })
    ResponseEntity<BaseResponse<Serializable>> doOnCreateCandidate(
            @Parameter(name = "candidate", description = "Candidate's information", required = true)
            CandidateDto candidate);

    @Operation(summary = "Update a Candidate", description = "Update a candidate's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Success response",
                            description = "Update a Candidate.",
                            value = JSON_RESPONSE_GET_SAVE_UPDATE)
            })),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Duplicate email response",
                            description = "Fail to create a Candidate.",
                            value = JSON_RESPONSE_DUPLICATE_EMAIL),
                    @ExampleObject(name = "Malformed parameters response",
                            description = "Fail to update a Candidate.",
                            value = JSON_RESPONSE_MALFORMED_PARAMETERS)
            })
            ),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Not Found response",
                            description = "Fail to retrieve a Candidate.",
                            value = JSON_RESPONSE_NOT_FOUND)
            }))
    })
    ResponseEntity<BaseResponse<Serializable>> doOnUpdateCandidate(
            @Parameter(name = "uuid", description = "Candidate's uuid", required = true)
            String uuid,
            @Parameter(name = "candidate", description = "Candidate's information", required = true)
            CandidateDto candidate);

    @Operation(summary = "Delete Candidate", description = "Delete a specific candidate by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Success response",
                            description = "Delete a Candidate.")
            })),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json"
                    , examples = {
                    @ExampleObject(name = "Not Found response",
                            description = "Fail to retrieve a Candidate.",
                            value = JSON_RESPONSE_NOT_FOUND)
            }))
    })
    ResponseEntity<BaseResponse<Serializable>> doOnDeleteCandidate(
            @Parameter(name = "uuid", description = "Candidate's uuid", required = true)
            String uuid);
}
