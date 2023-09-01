package com.odd.oddProject.dao;

import com.odd.oddProject.dto.LocationDto;
import com.odd.oddProject.dto.OddSrchFilterDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class LocationDao {

    private final SqlSessionTemplate sqlSession;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationDao.class);
    @Autowired
    LocationDao(SqlSessionTemplate sqlSession){
        this.sqlSession = sqlSession;
    }
    public int insertLocation(List<LocationDto> list){
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return sqlSession.insert("insertLocation",list);
    }
    public List<LocationDto> selectLocationList(OddSrchFilterDto searchObject){
        LOGGER.info("searchObject >> " +searchObject);
        LOGGER.info(">>"+sqlSession.selectList("selectLocation",searchObject));
        return sqlSession.selectList("selectLocation",searchObject);
    }

    public int deleteLocation() { return sqlSession.delete("deleteLocation"); }

    public int allCntLocation() {
        return sqlSession.selectOne("allCntLocation","");
    }

    public int updateAllDelYn() {
        return sqlSession.update("updateAllDelYn", "");
    }
}
