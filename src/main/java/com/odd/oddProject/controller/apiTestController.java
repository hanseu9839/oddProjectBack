package com.odd.oddProject.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.odd.oddProject.cmn.CmnUtil.transferAddressPosition;



@RestController
@RequestMapping
public class apiTestController {
    public Logger LOGGER = LoggerFactory.getLogger(apiTestController.class);
    @Value("${kaokao.apikey}")
    private String kaokaoOpenApiKey;
    /* Api 연결 테스트 */
    @GetMapping("/apiTest")
    public String apiGetSpring(@RequestParam String api){
        LOGGER.info(api);
        return api;
    }
    /* 주소 위도 경도로 변환하여 Client에 보내기 */
    @GetMapping("/addXyTest")
    public Map<String,String> transferAddress(@RequestParam String address) throws UnsupportedEncodingException, URISyntaxException, ParseException {
        Map<String,String> position = transferAddressPosition(address,kaokaoOpenApiKey);
        return position;
    }


}
