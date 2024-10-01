package com.echocano.seek.challenge.infrastructure.spring.rest.resources.implementation;

import com.echocano.seek.challenge.application.service.implementation.CandidateService;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.BaseResponse;
import com.echocano.seek.challenge.infrastructure.spring.rest.dto.CandidateDto;
import com.echocano.seek.challenge.infrastructure.spring.rest.resources.ICandidateController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * CandidateController
 * <p>
 * CandidateController class.
 * <p>
 * @author echocano
 * @since 10/1/24
 */

@Log4j2
@RequiredArgsConstructor
@RestController
public class CandidateController implements ICandidateController {

    private final CandidateService candidateService;

    @Override
    public ResponseEntity<BaseResponse> doOnGetCandidates() {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> doOnGetCandidate(String uuid) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> doOnCreateCandidate(CandidateDto candidate) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> doOnUpdateCandidate(String uuid, CandidateDto candidate) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> doOnDeleteCandidate(String uuid) {
        return null;
    }
}
