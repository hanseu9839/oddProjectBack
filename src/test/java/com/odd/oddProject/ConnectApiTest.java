package com.odd.oddProject;

import com.odd.oddProject.cmn.CmnUtil;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class ConnectApiTest {


    @Test
    @DisplayName("Api연결 테스트")
    void apiFetchTest() throws URISyntaxException, ParseException {
        String[] locations = {"guro","jongno","yangcheon","gwanak"};

        for(String location : locations){
            System.out.println(location);
            ResponseEntity<?> responseEntity= CmnUtil.selectApiLocation(location);
            System.out.println("getApi = " + responseEntity);
        }

    }

}
