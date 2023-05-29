package com.odd.oddProject.batch;

import com.odd.oddProject.service.OpenApiService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Component
@EnableAsync
public class BatchOddLocation {
    @Autowired
    OpenApiService openApiService;
    /**
     * oddLocation DB insert 배치
     */
    @Scheduled(fixedRate = 5000) // 5초마다 실행
    public void oddLocationDataInsert() throws URISyntaxException, ParseException {
        try{
            openApiService.getOpenApiUriData();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }

    }
}
