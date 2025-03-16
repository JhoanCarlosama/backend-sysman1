package com.example.sysman.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "materials")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_material")
    @SequenceGenerator(name = "seq_material", sequenceName = "material_id_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private String type;
    private Double price;

    @Column(name = "date_sale")
    private LocalDate dateSale;

    @Column(name = "date_purchase")
    private LocalDate datePurchase;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity city;
}
