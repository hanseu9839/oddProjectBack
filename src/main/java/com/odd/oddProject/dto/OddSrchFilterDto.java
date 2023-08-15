package com.odd.oddProject.dto;

import lombok.Data;
import lombok.Getter;

// 옷다담 Location 검색필터 값을 저장하기 위한 Dto
@Data
@Getter
public class OddSrchFilterDto {
    String city;
    String district;
    String dong;
    String fullName;
}
