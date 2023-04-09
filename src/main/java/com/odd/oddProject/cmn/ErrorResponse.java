package com.odd.oddProject.cmn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse{
    private final String status;
    private final String message;

    public ErrorResponse(CustomErrorCode errorCode){
        this.status = errorCode.getStatusCode();
        this.message = errorCode.getDetailMessage();
    }

    public static ResponseEntity<ErrorResponse> error(OddException e){
        return ResponseEntity
                .status(HttpStatus.valueOf(e.getCustomErrorCode().getStatusCode()))
                .body(ErrorResponse.builder()
                        .status(e.getCustomErrorCode().getStatusCode())
                        .message(e.getCustomErrorCode().getDetailMessage())
                        .build());
    }

}
