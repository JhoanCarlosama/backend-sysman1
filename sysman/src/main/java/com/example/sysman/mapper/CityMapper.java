package com.example.sysman.mapper;

import com.example.sysman.entities.CityEntity;
import com.example.sysman.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityEntity toData(City city);

    City toModel(CityEntity cityEntity);
}
