package com.odd.oddProject.controller;

import com.odd.oddProject.dto.LocationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/odd")
public class oddLocationController {

    @PostMapping
    public List<LocationDto> findLocation(@RequestBody locationData){

    }
}
