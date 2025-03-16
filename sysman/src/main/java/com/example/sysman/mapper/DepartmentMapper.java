package com.example.sysman.mapper;

import com.example.sysman.entities.DepartmentEntity;
import com.example.sysman.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentEntity toData(Department department);

    Department toModel(DepartmentEntity departmentEntity);
}
