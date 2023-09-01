package com.odd.oddProject.service;

import com.odd.oddProject.dto.LocationDto;

import java.util.List;

public interface BatchOddService {
    void insertLocationData(List<LocationDto> list);
    int deleteLocationData();

    int allCntLocation();

    int updateAllDelYn();
}
