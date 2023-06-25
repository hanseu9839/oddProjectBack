package com.odd.oddProject.batch;

import com.odd.oddProject.dao.LocationDao;
import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.service.BatchOddService;
import com.odd.oddProject.service.BatchOddServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

@Component
@EnableAsync
public class BatchOddLocation {
    OpenApiService openApiService;
    BatchOddService batchOddService;
    @Autowired
    BatchOddLocation(OpenApiService openApiService, BatchOddServiceImpl batchOddService){
        this.openApiService = openApiService;
        this.batchOddService = batchOddService;
    }
    private static Logger LOGGER = LoggerFactory.getLogger(BatchOddLocation.class);
    /**
     * 옷다담 oddLocation DB insert 배치
     */
    @Scheduled(cron = "0 30 0 * * *") //0시 30분 배치 실행
    //@Scheduled(fixedRate = 5000) // 5초마다 실행
    public void oddLocationDataInsert() throws URISyntaxException, ParseException {

        List<LocationDto> locationDtoList = new ArrayList<>();

        try{
            locationDtoList.addAll(openApiService.getKakaoOpenApiUri());
            locationDtoList.addAll(openApiService.getOpenApiFileData());
            return;
        }catch(Exception e){
            e.printStackTrace();
        }
        LOGGER.info("locationString >> "+locationDtoList.get(0));
        batchOddService.insertLocationData(locationDtoList);
        LOGGER.info("OddLocation Data Insert() locationDtoList >> "+ locationDtoList);
        LOGGER.info("===============Odd Location Insert Batch End =========================");
        return;
    }
}
