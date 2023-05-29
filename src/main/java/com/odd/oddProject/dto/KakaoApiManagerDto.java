package com.odd.oddProject.dto;

import com.odd.oddProject.cmn.CustomErrorCode;
import com.odd.oddProject.cmn.OddException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KakaoApiManagerDto {
    @Value("{{kaokao.apikey}")
    String key= "KakaoAK d8cf9d20f843e173c5fe88fc3fb944dc";
    private String addressRestUrl = "https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query=";
    private static final Logger LOGGER = LogManager.getLogger(KakaoApiManagerDto.class);
    /* 파라미터로 넘겨받은 주소로 위도 경도를 보내준다.*/
    public Map<String, String> tansferAddressPosition(String address) throws URISyntaxException, UnsupportedEncodingException, ParseException {
        ResponseEntity<String> response = kakaoApiUrlConnection(address);

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
    /* OpenApi데이터로 주소 넘겨 받을시 카카오 형식으로 주소 변환*/
    public List<LocationDto> KakaoOpenApifetch(List<String> list) throws UnsupportedEncodingException, URISyntaxException, ParseException, OddException {
        List<LocationDto> loc = new ArrayList<>();
        int kaKaoApiCallcount=0;
        for(String address: list){
            if(address.equals("")) continue;
           ResponseEntity<String> response = kakaoApiUrlConnection(address);
           JSONParser jsonParser = new JSONParser();
           JSONObject body = (JSONObject) jsonParser.parse(response.getBody().toString());
           JSONArray locationArray = (JSONArray) body.get("documents");

           if(locationArray.size()!=0){
                JSONObject getAddress = (JSONObject) locationArray.get(0);
                JSONObject getTargetData = (JSONObject) getAddress.get("address");
                LocationDto locationDto = LocationDto.builder()
                                                        .city((String)getTargetData.get("region_1depth_name"))
                                                        .district((String)getTargetData.get("region_2depth_name"))
                                                        .dong((String)getTargetData.get("region_3depth_h_name"))
                                                        .x(Double.parseDouble((String) getTargetData.get("x")))
                                                        .y(Double.parseDouble((String) getTargetData.get("y")))
                                                        .del_yn("Y")
                                                        .build();
                LOGGER.info(""+locationDto);
                kaKaoApiCallcount++;
                loc.add(locationDto);
           }
        }
        System.out.println(loc);
        LOGGER.info("kaKaoCallApiCount : "+ kaKaoApiCallcount);
        return loc;
    }

    /* 카카오 API URL로 연결하여 Response받는다.*/
    public ResponseEntity<String> kakaoApiUrlConnection(String address) throws UnsupportedEncodingException, URISyntaxException, ParseException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", key);

        HttpEntity<String> entity = new HttpEntity<>("parameter",headers);
        String encode = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String restUrl = addressRestUrl+encode;
        URI uri = new URI(restUrl);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        return response;

    }


}
