package com.odd.oddProject.cmn;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class OddException extends Exception{
    private CustomErrorCode customErrorCode;

    public OddException(CustomErrorCode errorCode) {
        this.customErrorCode = errorCode;
    }
}
