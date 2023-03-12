package com.odd.oddProject.service;

import com.odd.oddProject.dto.OpenApiManagerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenApiService {
    @Autowired
    OpenApiManagerDto openApiManagerDto;
    List<OpenApiManagerDto> openApiManagerList;
    private List<?> getOpenApiData(){

        return openApiManagerList;
    }
    private
}
