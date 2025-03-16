package com.example.sysman.service.impl;

import com.example.sysman.mapper.DepartmentMapper;
import com.example.sysman.model.Department;
import com.example.sysman.repository.DepartmentRepository;
import com.example.sysman.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository repository;

    @Autowired
    DepartmentMapper mapper;

    @Override
    public List<Department> findAll() {
        return Optional.of(repository.findAll()
                .stream().map(mapper::toModel).toList()).orElseThrow(()-> new RuntimeException("empty list"));
    }

    @Override
    public Department save(Department department) {
        return Optional.of(repository.save(mapper.toData(department)))
                .map(mapper::toModel).orElseThrow(() -> new RuntimeException("error saving department"));
    }

    @Override
    public Department update(Department department) {
        return mapper.toModel(repository.findById(department.getId())
                .map(departmentSaved -> {
                    departmentSaved.setCode(department.getCode());
                    departmentSaved.setName(department.getName());

                    return repository.save(departmentSaved);
                }).orElseThrow(() -> new RuntimeException("Department not found")));
    }

    @Override
    public Department findById(Long id) {
        return Optional.of(mapper.toModel(repository.findById(id).get())).orElseThrow(() -> new RuntimeException("city no found by id"));
    }
}
