package com.example.sysman.service.impl;

import com.example.sysman.mapper.CityMapper;
import com.example.sysman.mapper.MaterialMapper;
import com.example.sysman.model.Material;
import com.example.sysman.repository.CityRepository;
import com.example.sysman.repository.MaterialRepository;
import com.example.sysman.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialRepository repository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    MaterialMapper mapper;

    @Autowired
    CityMapper cityMapper;

    @Override
    public List<Material> findAll() {
        return Optional.of(repository.findAll()
                .stream().map(mapper::toModel).toList()).orElseThrow(()-> new RuntimeException("empty list"));
    }

    @Override
    public Material save(Material material) {
        material.setDateSale(LocalDate.parse(material.getDateSale().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        material.setDatePurchase(LocalDate.parse(material.getDatePurchase().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return Optional.of(repository.save(mapper.toData(material)))
                .map(mapper::toModel).orElseThrow(() -> new RuntimeException("error saving material"));
    }

    @Override
    public Material update(Material material) {
        return mapper.toModel(repository.findById(material.getId())
                .map(materialSaved -> {
                    materialSaved.setName(material.getName());
                    materialSaved.setDescription(material.getDescription());
                    materialSaved.setType(material.getType());
                    materialSaved.setPrice(material.getPrice());
                    materialSaved.setDateSale(material.getDateSale());
                    materialSaved.setDatePurchase(material.getDatePurchase());
                    materialSaved.setStatus(material.getStatus());

                    Optional.ofNullable(material.getCity())
                            .map(city -> cityRepository.findById(material.getCity().getId()))
                            .flatMap(optionalCity -> optionalCity)
                            .ifPresent(materialSaved::setCity);

                    return repository.save(materialSaved);
                })
                .orElseThrow(() -> new RuntimeException("Material not found")));
    }

    @Override
    public Material findById(Long id) {
        return Optional.of(mapper.toModel(repository.findById(id).get())).orElseThrow(() -> new RuntimeException("city not found by id"));
    }

    @Override
    public Material findByName(String name) {
        return Optional.of(mapper.toModel(repository.findByName(name).get())).orElseThrow(() -> new RuntimeException("city not found by name"));
    }
}
