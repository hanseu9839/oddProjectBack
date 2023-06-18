package com.odd.oddProject.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

@Builder
@Data
public class LocationDto  {

    String city;// 시
    String country; // 구
    String district; // 군
    String dong; // 동
    double x; // 위도
    double y; // 경도
    String del_yn; // 삭제 여부


}
