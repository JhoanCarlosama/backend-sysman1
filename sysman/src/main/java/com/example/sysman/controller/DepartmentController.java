package com.example.sysman.controller;

import com.example.sysman.model.Department;
import com.example.sysman.service.DepartmentService;
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
@RequestMapping(value = "api/department/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping(value="all")
    @Operation(summary = "Listar todos los departamentos", description = "Obtiene una lista de departamentos.")
    public EntityResponse<?> index() {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .body(service.findAll())
                .build();
    }

    @GetMapping(value = "/show/{id}")
    @Operation(summary = "Buscar un departamento", description = "Busca un departamento en la BD por Id.")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Crear un departamento", description = "Registra un nuevo departamento en la BD.")
    public EntityResponse<?> create(@RequestBody Department department) {
        return EntityResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("created")
                .body(Optional.of(service.save(department)).stream().toList())
                .build();
    }

    @PostMapping(value = "/update")
    @Operation(summary = "Editar un departamento", description = "Edita un departamento existente en la BD.")
    public EntityResponse<?> update(@RequestBody Department department) {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("updated")
                .body(Optional.of(service.update(department)).stream().toList())
                .build();
    }
}
