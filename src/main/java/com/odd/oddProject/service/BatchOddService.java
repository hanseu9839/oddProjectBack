package com.odd.oddProject.service;

import com.odd.oddProject.dto.LocationDto;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BatchOddService {
    void insertLocationData(List<LocationDto> list);
    void deleteLocationData(LocationDto dto);

    int allCntLocation();

    int updateAllDelYn();
}
