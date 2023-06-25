package com.odd.oddProject.service;

import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.oddSrchFilterDto;

import java.util.List;

public interface OddLocationService {
    List<LocationDto> oddFindLocation(String srchFilter);
}
