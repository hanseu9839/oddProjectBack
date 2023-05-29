package com.odd.oddProject.service;

import com.odd.oddProject.cmn.CmnUtil;
import com.odd.oddProject.cmn.OddException;
import com.odd.oddProject.dto.KakaoApiManagerDto;
import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.OpenApiFileManagerDto;
import com.odd.oddProject.dto.OpenApiManagerDto;
import com.opencsv.exceptions.CsvValidationException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component("service")
public class OpenApiService {
    @Autowired
    KakaoApiManagerDto kakaoApiManagerDto;
    private final Logger logger = LoggerFactory.getLogger(OpenApiService.class);

    // URI형식의 OpenApiData형식을 가져온다.
    public List<String> getOpenApiUriData() throws URISyntaxException, ParseException {
        List<String> openApiUriList = new ArrayList<>();
        String[] locations = {"guro","jongno","yangcheon","gwanak", "gwangjin", "dongjak"};
        for(String location : locations){
            openApiUriList.addAll(CmnUtil.selectApiLocation(location));
        }

        for(String uriData : openApiUriList){
            System.out.println("getOpenApiUriData>>"+uriData);
        }
        return openApiUriList;
    }

    /**
     * 엑셀 파일 데이터들을 가져와준다.
     * @return 파일 데이터들을 LocationDto를 통해서 가져온다.
     * @throws CsvValidationException
     * @throws OddException
     * @throws IOException
     * @throws URISyntaxException
     * @throws ParseException
     */
    public List<String> getOpenApiFileData() throws CsvValidationException, OddException, IOException, URISyntaxException, ParseException {
        List<String> openApiFileList = new ArrayList<>();ㄴ
        OpenApiFileManagerDto openApiFileManagerDto = new OpenApiFileManagerDto();
        openApiFileList = openApiFileManagerDto.OpenApiFileFetch();

        return openApiFileList;
    }

    /**
     * 
     */
    public List<LocationDto> getKakaoOpenApiUri() throws URISyntaxException, ParseException {
        List<String> openApiData = this.getOpenApiUriData();
        List<LocationDto> kakaoApiData = kakaoApiManagerDto.KakaoOpenApifetch(openApiData);
        return kakaoApiData;
    }
}
