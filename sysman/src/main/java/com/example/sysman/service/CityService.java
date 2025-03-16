package com.example.sysman.service;

import com.example.sysman.model.City;
import java.util.List;

public interface CityService {

    public List<City> findAll();

    public City save(City city);

    public City update(City city);

    public City findById(Long id);

    public City findByName(String name);
}
