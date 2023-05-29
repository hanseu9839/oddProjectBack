package com.odd.oddProject.dto;


import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public class OpenApiManagerDto {
    private String BaseUrl;
    private String serviceKey;
    private int page;
    private int perPage;
    private String uddi;
    private int locationNumber;
    private final Logger logger = LogManager.getLogger(OpenApiManagerDto.class);

    /*공공데이터 URL을 만들어준다. */
    private URI makeUrl() throws URISyntaxException {
        System.out.println("makeUrl>>"+BaseUrl+locationNumber+"/v1/uddi:"+uddi+"?page="+page+"&perPage="+perPage+"&serviceKey="+serviceKey);
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
    public List<String> getAllFetch() throws URISyntaxException, ParseException {
        RestTemplate template = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        getAllCount();
        ResponseEntity<String> apiData = template.exchange(makeUrl(), HttpMethod.GET, entity, String.class);
        ArrayList<String> targetPositionDatas  = (ArrayList<String>) getPosition(apiData);
        for(String target : targetPositionDatas){
            logger.info(target);
        }
        return targetPositionDatas;
    }
    /* JsonData의 위치 데이터를 가져와준다. */
    public List<String> getPosition(ResponseEntity<String> apiData) throws ParseException {
        List<String> getOpenApiPositions = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        String apiResult = apiData.getBody().toString();
        JSONObject targetJsonObject = (JSONObject) jsonParser.parse(apiResult);
        JSONArray targetJsonArray = (JSONArray) targetJsonObject.get("data");

        System.out.println("targetJsonArray >> "+targetJsonArray);
        for(Object targetApiData : targetJsonArray){
            String transferTargetData = targetApiData.toString();
            JSONObject realTarget = (JSONObject) jsonParser.parse(transferTargetData);

            if(realTarget.containsKey("위치"))
            {
                String targetPosition = (String) realTarget.get("위치");
                getOpenApiPositions.add(targetPosition);
            }else if(realTarget.containsKey("도로명주소")){
                String targetPosition = (String) realTarget.get("도로명주소");
                getOpenApiPositions.add(targetPosition);
            }else if(realTarget.containsKey("주소")){
                String targetPosition = (String) realTarget.get("주소");
                getOpenApiPositions.add(targetPosition);
            } else if(realTarget.containsKey("지번주소")){
                String targetPosition = (String) realTarget.get("지번주소");
                getOpenApiPositions.add(targetPosition);
            }

        }

        return getOpenApiPositions;
    }
}
