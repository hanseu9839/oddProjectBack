package com.odd.oddProject.cmn;

import com.odd.oddProject.domain.Location;
import com.odd.oddProject.dto.OpenApiManagerDto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* 공통으로 자주 사용하는 함수 */
@Service
@Component
public class CmnUtil {

    private static final String API_URL = "https://api.odcloud.kr/api/";
    private static OpenApiManagerDto openApiManagerDto;
    private static ArrayList<String> apiData;
    private static final int START_PAGE = 1;
    private static final int END_PAGE = 10;
    private static final Logger logger = LoggerFactory.getLogger(CmnUtil.class);


    /* 지역OpenApi 데이터 가져오는 함수 */
    public static List<String> selectApiLocation(String location,String openApiKey) throws URISyntaxException, ParseException {
        switch (location)
        {
            case "guro":
                openApiManagerDto = OpenApiManagerDto.builder()
                                                        .BaseUrl(API_URL)
                                                        .serviceKey(openApiKey)
                                                        .page(START_PAGE)
                                                        .perPage(END_PAGE)
                                                        .uddi("672059d4-1830-44af-97dd-cf0954b2ee86")
                                                        .locationNumber(15068871)
                                                        .build();
                apiData = (ArrayList<String>) openApiManagerDto.getAllFetch();
                break;
            case "yangcheon" :
                openApiManagerDto = OpenApiManagerDto.builder()
                                                        .BaseUrl(API_URL)
                                                        .serviceKey(openApiKey)
                                                        .page(START_PAGE)
                                                        .perPage(END_PAGE)
                                                        .uddi("ce842bd1-877f-41b0-b612-81bb77bdbb1d")
                                                        .locationNumber(15105196)
                                                        .build();
                apiData = (ArrayList<String>) openApiManagerDto.getAllFetch();
                break;
            case "jongno" :
                openApiManagerDto = OpenApiManagerDto.builder()
                                                        .BaseUrl(API_URL)
                                                        .serviceKey(openApiKey)
                                                        .page(START_PAGE)
                                                        .perPage(END_PAGE)
                                                        .uddi("94ddbdca-b180-4156-b4f0-8048116792f9")
                                                        .locationNumber(15104622)
                                                        .build();
                apiData = (ArrayList<String>) openApiManagerDto.getAllFetch();
                break;
            case "gwanak" :
                openApiManagerDto = OpenApiManagerDto.builder()
                        .BaseUrl(API_URL)
                        .serviceKey(openApiKey)
                        .page(START_PAGE)
                        .perPage(END_PAGE)
                        .uddi("6dec2a8d-6404-4318-8767-85419b3c45a0")
                        .locationNumber(15076398)
                        .build();
                apiData = (ArrayList<String>) openApiManagerDto.getAllFetch();
                break;
            case "dongjak" :
                openApiManagerDto = OpenApiManagerDto.builder()
                                                        .BaseUrl(API_URL)
                                                        .serviceKey(openApiKey)
                                                        .page(START_PAGE)
                                                        .perPage(END_PAGE)
                                                        .uddi("05be3e28-de40-426b-9198-e4ca5b3ceee7")
                                                        .locationNumber(15068021)
                                                        .build();
                apiData = (ArrayList<String>) openApiManagerDto.getAllFetch();
                break;
            case "gwangjin" :
                openApiManagerDto = OpenApiManagerDto.builder()
                                                        .BaseUrl(API_URL)
                                                        .serviceKey(openApiKey)
                                                        .page(START_PAGE)
                                                        .perPage(END_PAGE)
                                                        .uddi("d63e68bf-e03d-4d3c-a203-fd9add3d372c")
                                                        .locationNumber(15109594)
                                                        .build();
                apiData = (ArrayList<String>) openApiManagerDto.getAllFetch();
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

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject body = (JSONObject) jsonParser.parse(response.getBody().toString());
        logger.info(body+"");
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

    //JSON 데이터 형식 만들어주기
   public static Map<String,Object> wrapperJSON(List targetList) {
        Map<String,Object> result = new HashMap<>();
        result.put("locList", targetList);
        return result;
    }

}
