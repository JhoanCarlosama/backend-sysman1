package com.example.sysman;

import com.example.sysman.entities.DepartmentEntity;
import com.example.sysman.mapper.DepartmentMapper;
import com.example.sysman.model.Department;
import com.example.sysman.repository.DepartmentRepository;
import com.example.sysman.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository repository;

    @Mock
    private DepartmentMapper mapper;

    @InjectMocks
    private DepartmentServiceImpl service;

    private Department department;
    private DepartmentEntity entity;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("Cundinamarca");
        department.setCode("CUN");

        entity = new DepartmentEntity();
        entity.setId(1L);
        entity.setName("Cundinamarca");
        entity.setCode("CUN");
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toModel(any(DepartmentEntity.class))).thenReturn(department);

        List<Department> result = service.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Cundinamarca", result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(mapper.toData(any(Department.class))).thenReturn(entity);
        when(repository.save(any(DepartmentEntity.class))).thenReturn(entity);
        when(mapper.toModel(any(DepartmentEntity.class))).thenReturn(department);

        Department savedDepartment = service.save(department);

        assertNotNull(savedDepartment);
        assertEquals("Cundinamarca", savedDepartment.getName());
        verify(repository, times(1)).save(any(DepartmentEntity.class));
    }

    @Test
    void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(DepartmentEntity.class))).thenReturn(entity);
        when(mapper.toModel(any(DepartmentEntity.class))).thenReturn(department);

        Department updatedDepartment = service.update(department);

        assertNotNull(updatedDepartment);
        assertEquals("Cundinamarca", updatedDepartment.getName());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(DepartmentEntity.class));
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toModel(any(DepartmentEntity.class))).thenReturn(department);

        Department result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Cundinamarca", result.getName());
        verify(repository, times(1)).findById(1L);
    }
}
