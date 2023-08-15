package com.odd.oddProject.service;

import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.OddSrchFilterDto;

import java.util.List;

public interface OddLocationService {
    List<LocationDto> oddFindLocation(OddSrchFilterDto srchObject);
}
