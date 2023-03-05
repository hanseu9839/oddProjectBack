package com.odd.oddProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class apiTestController {
    public Logger LOGGER = LoggerFactory.getLogger(apiTestController.class);
    @GetMapping("/apiTest")
    public String apiGetSpring(@RequestParam String api){
        LOGGER.info(api);
        return api;
    }
}
