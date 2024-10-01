package com.echocano.seek.challenge.infrastructure.spring.rest.resources.implementation;

import com.echocano.seek.challenge.application.service.ICandidateService;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.BaseResponse;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.CandidateDto;
import com.echocano.seek.challenge.infrastructure.spring.rest.mapper.ICandidateDtoMapper;
import com.echocano.seek.challenge.infrastructure.spring.rest.resources.ICandidateController;
import com.echocano.seek.challenge.infrastructure.spring.rest.resources.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BaseResponse<List<CandidateDto>>> doOnGetCandidates() {
        List<CandidateDto> candidates = mapper.toDto(service.getCandidates());
        BaseResponse<List<CandidateDto>> response = new BaseResponse<>();
        response.setCode(ResponseCode.OK.getCode());
        response.setMessage(ResponseCode.OK.getMessage());
        response.setData(candidates);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/" + UUID_PARAMETER, produces = "application/json; charset=UTF-8")
    public ResponseEntity<BaseResponse<CandidateDto>> doOnGetCandidate(@PathVariable(UUID_PATH_VARIABLE) String uuid) {
        return null;
    }

    @Override
    @PostMapping(value = "/", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BaseResponse<CandidateDto>> doOnCreateCandidate(@RequestBody CandidateDto candidate) {
        return null;
    }

    @Override
    @PutMapping(value = "/" + UUID_PARAMETER, produces = "application/json; charset=UTF-8")
    public ResponseEntity<BaseResponse<CandidateDto>> doOnUpdateCandidate(@PathVariable(UUID_PATH_VARIABLE) String uuid
            , @RequestBody CandidateDto candidate) {
        return null;
    }

    @Override
    @DeleteMapping(value = "/" + UUID_PARAMETER, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Void> doOnDeleteCandidate(@PathVariable(UUID_PATH_VARIABLE) String uuid) {
        return null;
    }
}
