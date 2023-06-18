package com.odd.oddProject.mapper;

import com.odd.oddProject.dto.LocationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OddLocationMapper {

    // Odd 공공 API 날짜 데이터를 삽입한다.
    public void insertLocation(List<LocationDto> list);

    // Odd 공공 API 날짜 데이터를 삭제한다.
    public void delete(LocationDto dto);

    // Odd 공공 API 날짜 데이터를 가져온다.
    public List<LocationDto> getOddList(String searchValue);
}
