package com.odd.oddProject.repository;

import com.odd.oddProject.dto.LocationDto;
import org.springframework.stereotype.Repository;


public interface LocationRepository {
    void save(LocationDto locationDto);
}
