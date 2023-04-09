package com.odd.oddProject;

import com.odd.oddProject.cmn.CmnUtil;
import com.odd.oddProject.cmn.OddException;
import com.odd.oddProject.dto.KakaoApiManagerDto;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Component
public class ConnectApiTest {


    @Test
    @DisplayName("Api연결 테스트")
    void apiFetchTest() throws URISyntaxException, ParseException {
        String[] locations = {"guro","jongno","yangcheon","gwanak"};

        for(String location : locations){
            System.out.println("location"+location);
            ArrayList<String> responseEntity = (ArrayList<String>) CmnUtil.selectApiLocation(location);
            System.out.println("getApi = " + responseEntity);
        }

    }
    @Test
    @DisplayName("자바 현재 디렉토리 확인")
    void currentDirectFind() {
        String path = System.getProperty("user.dir");
        System.out.println("Working Directory = " + path);
    }
    @Test
    @DisplayName("KaokaoOpenApi 연결 테스트")
    void connectKaokaoApi() throws URISyntaxException, ParseException, UnsupportedEncodingException, OddException {
        String location = "guro";
        ArrayList<String> responseEntity = (ArrayList<String>) CmnUtil.selectApiLocation(location);
        KakaoApiManagerDto kakaoApiManagerDto = new KakaoApiManagerDto();
        kakaoApiManagerDto.fetchKakaoApi(responseEntity);
    }

}
