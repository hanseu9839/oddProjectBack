package com.odd.oddProject.cmn;

import com.odd.oddProject.dto.OpenApiManagerDto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
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
    /* Kaokao openApi 사용하여 한개 데이터 주소를 위도 경도로 바꿔주기*/
    public static Map<String,String> transferAddressPosition(String address,String kaokaoOpenApiKey) throws UnsupportedEncodingException, URISyntaxException, ParseException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", kaokaoOpenApiKey);

        HttpEntity<String> entity = new HttpEntity<>("parameter", headers);
        String encode = URLEncoder.encode(address, "UTF-8");
        String restUrl = "https://dapi.kakao.com/v2/local/search/address.json?analyze_type=exact&page=1&size=10&query=" + encode;
        URI uri = new URI(restUrl);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject body = (JSONObject) jsonParser.parse(response.getBody().toString());
        JSONArray locationArray = (JSONArray) body.get("documents");

        Map<String,String> position = new HashMap<>();
        if(locationArray.size()!=0){
            JSONObject getAddress = (JSONObject) locationArray.get(0);
            JSONObject getLocation = (JSONObject) getAddress.get("address");

            if(getLocation.size() != 0){
                position.put("lag", (String) getLocation.get("x"));
                position.put("lat", (String) getLocation.get("y"));
            }
        }

        return position;
    }


}
