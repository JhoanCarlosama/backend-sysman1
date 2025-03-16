package com.example.sysman.service;

import com.example.sysman.model.Material;

import java.util.List;

public interface MaterialService {

    public List<Material> findAll();

    public Material save(Material material);

    public Material update(Material material);

    public Material findById(Long id);

    public Material findByName(String name);
}
