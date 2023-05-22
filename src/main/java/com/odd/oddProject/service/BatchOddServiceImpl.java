package com.odd.oddProject.service;


import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.mapper.OddLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchOddServiceImpl implements  BatchOddService{
    @Autowired
    OddLocationMapper oddLocationMapper;


    @Override
    public void insertLocationData(LocationDto dto) {
        oddLocationMapper.insert();
    }

    public void deleteLocationData() throws Exception{
        oddLocationMapper.delete();
    }
}
