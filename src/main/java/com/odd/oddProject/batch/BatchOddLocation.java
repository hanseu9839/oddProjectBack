package com.odd.oddProject.batch;

import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.service.OpenApiService;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Component
@EnableAsync
public class BatchOddLocation {
    @Autowired
    OpenApiService openApiService;

    private static Logger LOGGER = LoggerFactory.getLogger(BatchOddLocation.class);
    /**
     * oddLocation DB insert 배치
     */
    @Scheduled(fixedRate = 5000) // 5초마다 실행
    public void oddLocationDataInsert() throws URISyntaxException, ParseException {

        List<LocationDto> locationDtoList = null;
        LOGGER.info("===============Odd Location Insert Batch START ==========================");
        try{
            locationDtoList= openApiService.getKakaoOpenApiUri();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        LOGGER.info("OddLocation Data Insert() locationDtoList >> "+ locationDtoList);
        LOGGER.info("===============Odd Location Insert Batch End =========================");
    }
}
