package com.example.sysman.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Material {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Double price;
    private LocalDate dateSale;
    private LocalDate datePurchase;
    private String status;
    private City city;
}
