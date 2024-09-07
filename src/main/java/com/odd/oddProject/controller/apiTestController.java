package com.odd.oddProject.controller;

import com.odd.oddProject.cmn.OddException;
import com.odd.oddProject.service.OpenApiService;
import com.opencsv.exceptions.CsvValidationException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @Autowired
    private OpenApiService openApiService;
    @Value("${file.path}")
    private String filePath;

    /* 주소 위도 경도로 변환하여 Client에 보내기 */
    @GetMapping("/api/srchFilter")
    @CrossOrigin(origins = "http://34.64.95.196:3000")
    public Map<String,String> transferAddress(@RequestParam String address) throws UnsupportedEncodingException, URISyntaxException, ParseException {
        Map<String,String> position = transferAddressPosition(address,kaokaoOpenApiKey);
        LOGGER.info("position>>"+position);
        return position;
    }

    @PostMapping("/api/testFile")
    public void excelFile() throws CsvValidationException, OddException, IOException, URISyntaxException, ParseException {
        openApiService.getOpenApiFileData();
    }
}
