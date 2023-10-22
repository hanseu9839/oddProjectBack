package com.odd.oddProject.controller;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

import static com.odd.oddProject.cmn.CmnUtil.transferAddressPosition;



@RestController
@RequestMapping("/odds")
public class apiTestController {
    public Logger LOGGER = LoggerFactory.getLogger(apiTestController.class);
    @Value("${kaokao.apikey}")
    private String kaokaoOpenApiKey;

    @Value("${file.path}")
    private String filePath;
    /* Api 연결 테스트 */
    @GetMapping("/apiTest")
    public String apiGetSpring(@RequestParam String api){
        LOGGER.info(api);
        LOGGER.info("File Path >>"+filePath);
        return "TEST";
    }
    /* 주소 위도 경도로 변환하여 Client에 보내기 */
    @GetMapping("/srchFilter")
    //@CrossOrigin(origins = "http://localhost:3000")
    public Map<String,String> transferAddress(@RequestParam String address) throws UnsupportedEncodingException, URISyntaxException, ParseException {
        Map<String,String> position = transferAddressPosition(address,kaokaoOpenApiKey);
        LOGGER.info("position>>"+position);
        return position;
    }
}
