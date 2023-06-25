package com.odd.oddProject.service;

import com.odd.oddProject.dao.LocationDao;
import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.oddSrchFilterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OddLocationServiceImpl implements OddLocationService{
    LocationDao locationDao;

    OddLocationServiceImpl(LocationDao locationDao){
        this.locationDao = locationDao;
    }
    // 옷다담 DB에 저장되어있는 Location을 가져온다.
    @Override
    public List<LocationDto> oddFindLocation(String srchFilter) {
        return locationDao.selectLocationList(srchFilter);
    }
}
