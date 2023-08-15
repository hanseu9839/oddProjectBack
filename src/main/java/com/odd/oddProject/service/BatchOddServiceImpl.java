package com.odd.oddProject.service;


import com.odd.oddProject.dao.LocationDao;
import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.mapper.OddLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BatchOddServiceImpl implements  BatchOddService{
    @Autowired
    OddLocationMapper oddLocationMapper;
    @Autowired
    LocationDao locationDao;
    @Override
    public void insertLocationData(List<LocationDto> list) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        locationDao.insertLocation(list);
    }

    @Override
    public void deleteLocationData(LocationDto dto) {
        oddLocationMapper.delete(dto);
    }
    @Override
    public int allCntLocation() { return locationDao.allCntLocation(); }

    @Override
    public int updateAllDelYn() { return locationDao.updateAllDelYn(); }

}

