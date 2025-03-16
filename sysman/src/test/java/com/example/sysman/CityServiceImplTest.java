package com.example.sysman;

import com.example.sysman.entities.CityEntity;
import com.example.sysman.mapper.CityMapper;
import com.example.sysman.model.City;
import com.example.sysman.repository.CityRepository;
import com.example.sysman.repository.DepartmentRepository;
import com.example.sysman.service.impl.CityServiceImp;
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
public class CityServiceImplTest {
    @Mock
    private CityRepository repository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private CityMapper mapper;

    @InjectMocks
    private CityServiceImp service;

    private City city;
    private CityEntity entity;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(1L);
        city.setName("Bogotá");
        city.setCode("BOG");

        entity = new CityEntity();
        entity.setId(1L);
        entity.setName("Bogotá");
        entity.setCode("BOG");
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toModel(any(CityEntity.class))).thenReturn(city);

        List<City> result = service.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Bogotá", result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(mapper.toData(any(City.class))).thenReturn(entity);
        when(repository.save(any(CityEntity.class))).thenReturn(entity);
        when(mapper.toModel(any(CityEntity.class))).thenReturn(city);

        City savedCity = service.save(city);

        assertNotNull(savedCity);
        assertEquals("Bogotá", savedCity.getName());
        verify(repository, times(1)).save(any(CityEntity.class));
    }

    @Test
    void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(CityEntity.class))).thenReturn(entity);
        when(mapper.toModel(any(CityEntity.class))).thenReturn(city);

        City updatedCity = service.update(city);

        assertNotNull(updatedCity);
        assertEquals("Bogotá", updatedCity.getName());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(CityEntity.class));
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toModel(any(CityEntity.class))).thenReturn(city);

        City result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Bogotá", result.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindByName() {
        when(repository.findByName("Bogotá")).thenReturn(Optional.of(entity));
        when(mapper.toModel(any(CityEntity.class))).thenReturn(city);

        City result = service.findByName("Bogotá");

        assertNotNull(result);
        assertEquals("Bogotá", result.getName());
        verify(repository, times(1)).findByName("Bogotá");
    }
}
