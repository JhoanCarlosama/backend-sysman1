package com.example.sysman;

import com.example.sysman.entities.MaterialEntity;
import com.example.sysman.mapper.CityMapper;
import com.example.sysman.mapper.MaterialMapper;
import com.example.sysman.model.Material;
import com.example.sysman.repository.CityRepository;
import com.example.sysman.repository.MaterialRepository;
import com.example.sysman.service.impl.MaterialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialServiceImplTest {
    @Mock
    private MaterialRepository repository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private MaterialMapper mapper;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private MaterialServiceImpl service;

    private Material material;
    private MaterialEntity entity;

    @BeforeEach
    void setUp() {
        material = new Material();
        material.setId(1L);
        material.setName("Wood");
        material.setDescription("Pine wood");
        material.setType("Lumber");
        material.setPrice(100.0);
        material.setDateSale(LocalDate.of(2024, 3, 16));
        material.setDatePurchase(LocalDate.of(2024, 3, 10));
        material.setStatus("Available");

        entity = new MaterialEntity();
        entity.setId(1L);
        entity.setName("Wood");
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toModel(any(MaterialEntity.class))).thenReturn(material);

        List<Material> result = service.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Wood", result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(mapper.toData(any(Material.class))).thenReturn(entity);
        when(repository.save(any(MaterialEntity.class))).thenReturn(entity);
        when(mapper.toModel(any(MaterialEntity.class))).thenReturn(material);

        Material savedMaterial = service.save(material);

        assertNotNull(savedMaterial);
        assertEquals("Wood", savedMaterial.getName());
        verify(repository, times(1)).save(any(MaterialEntity.class));
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toModel(any(MaterialEntity.class))).thenReturn(material);

        Material result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Wood", result.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(MaterialEntity.class))).thenReturn(entity);
        when(mapper.toModel(any(MaterialEntity.class))).thenReturn(material);

        Material updatedMaterial = service.update(material);

        assertNotNull(updatedMaterial);
        assertEquals("Wood", updatedMaterial.getName());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(MaterialEntity.class));
    }

    @Test
    void testFindByName() {
        when(repository.findByName("Wood")).thenReturn(Optional.of(entity));
        when(mapper.toModel(any(MaterialEntity.class))).thenReturn(material);

        Material result = service.findByName("Wood");

        assertNotNull(result);
        assertEquals("Wood", result.getName());
        verify(repository, times(1)).findByName("Wood");
    }
}
