package com.echocano.seek.challenge.infrastructure.spring.rest.resources.implementation;

import com.echocano.seek.challenge.application.service.ICandidateService;
import com.echocano.seek.challenge.domain.exceptions.CandidateExistException;
import com.echocano.seek.challenge.domain.exceptions.CandidateNotFoundException;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.BaseResponse;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.CandidateDto;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.Error;
import com.echocano.seek.challenge.infrastructure.spring.rest.mapper.ICandidateDtoMapper;
import com.echocano.seek.challenge.infrastructure.spring.rest.resources.ICandidateController;
import com.echocano.seek.challenge.infrastructure.spring.rest.resources.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * CandidateController
 * <p>
 * CandidateController class.
 * <p>
 *
 * @author echocano
 * @since 10/1/24
 */

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/candidates")
public class CandidateController implements ICandidateController {

    private static final String UUID_PARAMETER = "{uuid}";
    private static final String UUID_PATH_VARIABLE = "uuid";

    private final ICandidateService service;
    private final ICandidateDtoMapper mapper;

    @Override
    @GetMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<CandidateDto>>> doOnGetCandidates() {
        List<CandidateDto> candidates = mapper.toDto(service.getCandidates());
        BaseResponse<List<CandidateDto>> response = new BaseResponse<>();
        response.setCode(ResponseCode.OK.getCode());
        response.setMessage(ResponseCode.OK.getMessage());
        response.setData(candidates);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/" + UUID_PARAMETER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Serializable>> doOnGetCandidate(@PathVariable(UUID_PATH_VARIABLE) String uuid) {
        try {
            CandidateDto candidate = mapper.toDto(service.getCandidate(uuid));
            return new ResponseEntity<>(BaseResponse.buildResponse(ResponseCode.OK, candidate), HttpStatus.OK);
        } catch (CandidateNotFoundException e) {
            return new ResponseEntity<>(
                    BaseResponse.buildResponse(ResponseCode.NOT_FOUND, Error.builder().message(e.getMessage()).build())
                    , HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Serializable>> doOnCreateCandidate(@RequestBody CandidateDto candidate) {
        try {
            candidate = mapper.toDto(service.createCandidate(mapper.toDomain(candidate)));
            return new ResponseEntity<>(BaseResponse.buildResponse(ResponseCode.OK_CREATED, candidate), HttpStatus.CREATED);
        } catch (CandidateExistException e) {
            return new ResponseEntity<>(
                    BaseResponse.buildResponse(ResponseCode.BAD_REQUEST, Error.builder().message(e.getMessage()).build())
                    , HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PutMapping(value = "/" + UUID_PARAMETER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Serializable>> doOnUpdateCandidate(@PathVariable(UUID_PATH_VARIABLE) String uuid
            , @RequestBody CandidateDto candidate) {
        try {
            candidate = mapper.toDto(service.updateCandidate(mapper.toDomain(candidate)));
            return new ResponseEntity<>(BaseResponse.buildResponse(ResponseCode.OK, candidate), HttpStatus.OK);
        } catch (CandidateNotFoundException e) {
            return new ResponseEntity<>(
                    BaseResponse.buildResponse(ResponseCode.BAD_REQUEST, Error.builder().message(e.getMessage()).build())
                    , HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping(value = "/" + UUID_PARAMETER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Serializable>> doOnDeleteCandidate(@PathVariable(UUID_PATH_VARIABLE) String uuid) {
        try {
            service.deleteCandidate(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CandidateNotFoundException e) {
            return new ResponseEntity<>(
                    BaseResponse.buildResponse(ResponseCode.BAD_REQUEST, Error.builder().message(e.getMessage()).build())
                    , HttpStatus.BAD_REQUEST);
        }
    }
}
