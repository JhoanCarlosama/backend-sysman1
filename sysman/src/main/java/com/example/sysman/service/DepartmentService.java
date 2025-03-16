package com.example.sysman.service;

import com.example.sysman.model.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> findAll();

    public Department save(Department department);

    public Department update(Department department);

    public Department findById(Long id);
}
