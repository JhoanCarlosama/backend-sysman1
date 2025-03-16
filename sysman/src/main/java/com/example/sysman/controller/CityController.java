package com.example.sysman.controller;

import com.example.sysman.model.City;
import com.example.sysman.service.CityService;
import com.example.sysman.utils.EntityResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/city/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CityController {
    @Autowired
    CityService service;

    @GetMapping(value="all")
    @Operation(summary = "Listar todas las ciudades", description = "Obtiene una lista de las ciudades.")
    public EntityResponse<?> index() {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .body(service.findAll())
                .build();
    }

    @GetMapping(value = "/show/{id}")
    @Operation(summary = "Buscar una ciudad", description = "Busca una ciudad en la BD por Id.")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Crear una ciudad", description = "Registra una nueva ciudad en la BD.")
    public EntityResponse<?> create(@RequestBody City city) {
        return EntityResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("created")
                .body(Optional.of(service.save(city)).stream().toList())
                .build();
    }

    @PostMapping(value = "/update")
    @Operation(summary = "Editar una ciudad", description = "Edita una ciudad existente en la BD.")
    public EntityResponse<?> update(@RequestBody City city) {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("updated")
                .body(Optional.of(service.update(city)).stream().toList())
                .build();
    }
}
