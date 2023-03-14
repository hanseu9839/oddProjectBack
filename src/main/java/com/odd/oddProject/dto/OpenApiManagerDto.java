package com.odd.oddProject.dto;


import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


@AllArgsConstructor
public class OpenApiManagerDto {
    private String BaseUrl;
    private String serviceKey;
    private int page;
    private int perPage;
    private String uddi;
    private int locationNumber;
    private final Logger logger = LoggerFactory.getLogger(OpenApiManagerDto.class);

    /*공공데이터 URL을 만들어준다. */
    private URI makeUrl() throws URISyntaxException {
        return new URI(BaseUrl+locationNumber+"/v1/uddi:"+uddi+"?page="+page+"&perPage="+perPage+"&serviceKey="+serviceKey);
    }

    /* 각 Api의 totalCount(총 개수를 불러온다.) */
    public void getAllCount() throws URISyntaxException, ParseException {
        RestTemplate template = new RestTemplate();
        JSONParser jsonParser = new JSONParser();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<String> resultMap = null;
        try {
            resultMap = template.exchange(makeUrl(), HttpMethod.GET, entity, String.class);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(resultMap.getBody());
            perPage = Integer.parseInt(String.valueOf(jsonObject.get("totalCount")));
            logger.info("perPage : " +perPage);
        } catch (HttpClientErrorException e) {
            logger.info(String.valueOf(e.getStatusCode()));
            logger.info(e.getResponseBodyAsString());
        }
    }

    /*모든 API의 개수를 가져와준다.*/
    public ResponseEntity<?> getAllFetch() throws URISyntaxException, ParseException {
        RestTemplate template = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        getAllCount();
        ResponseEntity<Map> apiData = template.exchange(makeUrl(), HttpMethod.GET, entity, Map.class);

        return apiData;
    }
}
