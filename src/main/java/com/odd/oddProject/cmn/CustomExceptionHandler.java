package com.odd.oddProject.cmn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = OddException.class)
    public ResponseEntity<ErrorResponse> handleOddException(OddException e){
        log.error("[oddCustomException] {} : {}",e.getCustomErrorCode().getStatusCode(),e.getCustomErrorCode().getDetailMessage());
        return ErrorResponse.error(e);
    }
}
