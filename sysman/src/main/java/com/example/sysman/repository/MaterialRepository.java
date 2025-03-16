package com.example.sysman.repository;

import com.example.sysman.entities.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

    Optional<MaterialEntity> findByName(@Param("name") String name);
}
