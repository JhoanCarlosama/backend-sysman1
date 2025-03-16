package com.example.sysman.controller;

import com.example.sysman.model.City;
import com.example.sysman.service.CityService;
import com.example.sysman.utils.EntityResponse;
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
    public EntityResponse<?> index() {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .body(service.findAll())
                .build();
    }

    @GetMapping(value = "/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PostMapping(value = "/create")
    public EntityResponse<?> create(@RequestBody City city) {
        return EntityResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("created")
                .body(Optional.of(service.save(city)).stream().toList())
                .build();
    }

    @PostMapping(value = "/update")
    public EntityResponse<?> update(@RequestBody City city) {
        return EntityResponse.builder()
                .code(HttpStatus.OK.value())
                .message("updated")
                .body(Optional.of(service.update(city)).stream().toList())
                .build();
    }
}
