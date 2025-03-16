package com.example.sysman.mapper;

import com.example.sysman.entities.MaterialEntity;
import com.example.sysman.model.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    MaterialEntity toData(Material material);

    Material toModel(MaterialEntity materialEntity);
}
