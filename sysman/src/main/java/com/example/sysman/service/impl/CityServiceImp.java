package com.example.sysman.service.impl;

import com.example.sysman.mapper.CityMapper;
import com.example.sysman.model.City;
import com.example.sysman.repository.CityRepository;
import com.example.sysman.repository.DepartmentRepository;
import com.example.sysman.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CityServiceImp implements CityService {
    @Autowired
    CityRepository repository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CityMapper mapper;

    @Override
    public List<City> findAll() {
        return Optional.of(repository.findAll()
                .stream().map(mapper::toModel).toList()).orElseThrow(()-> new RuntimeException("empty list"));
    }

    @Override
    public City save(City city) {
        return Optional.of(repository.save(mapper.toData(city)))
                .map(mapper::toModel).orElseThrow(() -> new RuntimeException("error saving city"));
    }

    @Override
    public City update(City city) {
        return mapper.toModel(repository.findById(city.getId())
                .map(citySaved -> {
                    citySaved.setCode(city.getCode());
                    citySaved.setName(city.getName());

                    Optional.ofNullable(city.getDepartment())
                            .map(department -> departmentRepository.findById(city.getDepartment().getId()))
                            .flatMap(optionalDepartment -> optionalDepartment)
                            .ifPresent(citySaved::setDepartment);

                    return repository.save(citySaved);
                }).orElseThrow(() -> new RuntimeException("City not found")));
    }

    @Override
    public City findById(Long id) {
        return Optional.of(mapper.toModel(repository.findById(id).get())).orElseThrow(() -> new RuntimeException("city no found by id"));
    }

    @Override
    public City findByName(String name) {
        return Optional.of(mapper.toModel(repository.findByName(name).get())).orElseThrow(() -> new RuntimeException("city not found by name"));
    }
}
