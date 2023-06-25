package com.odd.oddProject.controller;

import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.oddSrchFilterDto;
import com.odd.oddProject.service.OddLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/odds")
public class oddLocationController {
    @Autowired
    OddLocationService oddLocationService;

    @GetMapping("/searchLocation")
    public ModelAndView findLocation(@RequestParam(required = false "keyword") String keyword, ModelAndView mav){
        List<LocationDto> list = oddLocationService.oddFindLocation(keyword);
        mav.addObject("list", "Hello, World!");
        mav.setViewName("/odd/main");
        return mav;
    }
}
