package com.odd.oddProject.cmn;

import com.odd.oddProject.dto.OpenApiManagerDto;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Map;
/* 공통으로 자주 사용하는 함수 */
@Service
public class CmnUtil {
    private static OpenApiManagerDto openApiManagerDto;
    private static ResponseEntity<Map> apiData;
    private static final int constPerPage = 10;
    /* 지역OpenApi 데이터 가져오는 함수 */
    public static ResponseEntity<?> selectApiLocation(String location) throws URISyntaxException, ParseException {
        switch (location)
        {
            case "guro":
                openApiManagerDto = new OpenApiManagerDto("https://api.odcloud.kr/api/","Z%2BQodRDulSnhc%2FQPPMSjFBJmRBoB2lmzYIoADFND8d%2Fv7M%2BrFCLklDNG2HS7BZjwCGdMARLWssYcIL5pzX%2FgSw%3D%3D",1,constPerPage,"672059d4-1830-44af-97dd-cf0954b2ee86",15068871);
                apiData = (ResponseEntity<Map>) openApiManagerDto.getAllFetch();
                break;
            case "yangcheon":
                openApiManagerDto = new OpenApiManagerDto("https://api.odcloud.kr/api/","Z%2BQodRDulSnhc%2FQPPMSjFBJmRBoB2lmzYIoADFND8d%2Fv7M%2BrFCLklDNG2HS7BZjwCGdMARLWssYcIL5pzX%2FgSw%3D%3D", 1, constPerPage,"ce842bd1-877f-41b0-b612-81bb77bdbb1d",15105196);
                apiData = (ResponseEntity<Map>) openApiManagerDto.getAllFetch();
                break;
            case "jongno":
                openApiManagerDto = new OpenApiManagerDto("https://api.odcloud.kr/api/","Z%2BQodRDulSnhc%2FQPPMSjFBJmRBoB2lmzYIoADFND8d%2Fv7M%2BrFCLklDNG2HS7BZjwCGdMARLWssYcIL5pzX%2FgSw%3D%3D",1,constPerPage, "94ddbdca-b180-4156-b4f0-8048116792f9",15104622);
                apiData = (ResponseEntity<Map>) openApiManagerDto.getAllFetch();
                break;
            case "gwanak":
                openApiManagerDto = new OpenApiManagerDto("https://api.odcloud.kr/api/","Z%2BQodRDulSnhc%2FQPPMSjFBJmRBoB2lmzYIoADFND8d%2Fv7M%2BrFCLklDNG2HS7BZjwCGdMARLWssYcIL5pzX%2FgSw%3D%3D",1,constPerPage, "6dec2a8d-6404-4318-8767-85419b3c45a0",15076398);
                apiData = (ResponseEntity<Map>) openApiManagerDto.getAllFetch();
                break;

        }
        return apiData;
    }

}
