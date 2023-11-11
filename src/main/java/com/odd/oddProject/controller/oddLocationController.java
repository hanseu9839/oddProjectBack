package com.odd.oddProject.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odd.oddProject.cmn.CmnUtil;
import com.odd.oddProject.cmn.ErrorResponse;
import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.OddSrchFilterDto;
import com.odd.oddProject.service.OddLocationService;
import com.odd.oddProject.service.OddLocationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/odds")
public class oddLocationController {

    OddLocationService oddLocationService;

    @Autowired
    oddLocationController(OddLocationServiceImpl oddLocationService){
        this.oddLocationService = oddLocationService;
    }
    private static Logger LOGGER = LoggerFactory.getLogger(OddLocationService.class);
    @PostMapping("/api/search")
    @CrossOrigin(origins = "http://43.200.171.27:3000")
    public ResponseEntity findLocation(@RequestBody OddSrchFilterDto search){
        LOGGER.info("search "+search);
        List<LocationDto> list= oddLocationService.oddFindLocation(search);
        if(list == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("201","NOT FOUND"));
        }
        HashMap<String, Object> map = (HashMap<String, Object>) CmnUtil.wrapperJSON(list);

        return ResponseEntity.ok(map);
    }

}
