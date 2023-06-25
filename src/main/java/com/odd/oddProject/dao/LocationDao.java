package com.odd.oddProject.dao;

import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.oddSrchFilterDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class LocationDao {

    private final SqlSessionTemplate sqlSession;

    @Autowired
    LocationDao(SqlSessionTemplate sqlSession){
        this.sqlSession = sqlSession;
    }
    public int insertLocation(List<LocationDto> list){
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return sqlSession.insert("insertLocation",list);
    }
    public List<LocationDto> selectLocationList(String srchFilter){
        return sqlSession.selectList("selectLocation",srchFilter);
    }
}
