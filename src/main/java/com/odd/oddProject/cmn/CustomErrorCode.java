package com.odd.oddProject.cmn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    NO_LOCATION("600","위치 정보가 없습니다.");

    private final String statusCode;
    private final String detailMessage;
}
