package com.odd.oddProject.controller;

import com.odd.oddProject.batch.BatchOddLocation;
import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.oddSrchFilterDto;
import com.odd.oddProject.service.BatchOddServiceImpl;
import com.odd.oddProject.service.OddLocationService;
import com.odd.oddProject.service.OddLocationServiceImpl;
import com.odd.oddProject.service.OpenApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping("/main")
    public ModelAndView findLocation(@RequestParam(required = false, name="keyword") String keyword, ModelAndView mav){
        List<LocationDto> list= oddLocationService.oddFindLocation(keyword);
        LOGGER.info("locationDto" + list);
        mav.addObject("list", "Hello, World!");
        mav.setViewName("/odd/main");
        return mav;
    }

}
