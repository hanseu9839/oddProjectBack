package com.odd.oddProject.dto;

import lombok.Data;

// 옷다담 Location 검색필터 값을 저장하기 위한 Dto
@Data
public class oddSrchFilterDto {
    String srchCity;
    String srchCOUNTRY;
    String srchDistrict;
    String srchDong;
}
