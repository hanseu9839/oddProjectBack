package com.odd.oddProject.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationDto {
    String city;
    String country;
    String district;
    String dong;
    double x;
    double y;

}
