package com.odd.oddProject.service;

import com.odd.oddProject.cmn.CmnUtil;
import com.odd.oddProject.dto.OpenApiManagerDto;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Service
public class OpenApiService {

    List<Object> openApiList;
    private List<Object> getOpenApiData() throws URISyntaxException, ParseException {
        String[] locations = {"guro","jongno","yangcheon","gwanak"};
        for(String location : locations){
            ResponseEntity<?> responseEntity = CmnUtil.selectApiLocation(location);
            openApiList.add(responseEntity);
        }
        return openApiList;
    }

}
