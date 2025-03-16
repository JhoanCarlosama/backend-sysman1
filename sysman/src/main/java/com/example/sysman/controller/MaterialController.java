package com.example.sysman.controller;

import com.example.sysman.entities.MaterialEntity;
import com.example.sysman.mapper.MaterialMapper;
import com.example.sysman.model.Material;
import com.example.sysman.repository.MaterialRepository;
import com.example.sysman.service.MaterialService;
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
@RequestMapping(value = "api/material/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MaterialController {
    @Autowired
    MaterialService service;

    @GetMapping(value="all")
    @Operation(summary = "Listar todos los materiales", description = "Obtiene una lista de materiales.")
    public EntityResponse<?> index() {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .body(service.findAll())
                .build();
    }

    @GetMapping(value = "/show/{id}")
    @Operation(summary = "Buscar un material", description = "Busca un material en la BD por Id.")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Crear un material", description = "Registra un nuevo material en la BD.")
    public EntityResponse<?> create(@RequestBody Material material) {
        return EntityResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("created")
                .body(Optional.of(service.save(material)).stream().toList())
                .build();
    }

    @PostMapping(value = "/update")
    @Operation(summary = "Editar un material", description = "Edita un material existente en la BD.")
    public EntityResponse<?> update(@RequestBody Material material) {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("updated")
                .body(Optional.of(service.update(material)).stream().toList())
                .build();
    }

    @GetMapping(value = "/search/name/{name}")
    @Operation(summary = "Buscar un material por nombre", description = "Busca un material en la BD por nombre.")
    public ResponseEntity searchByName(@PathVariable String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findByName(name));
    }

}
