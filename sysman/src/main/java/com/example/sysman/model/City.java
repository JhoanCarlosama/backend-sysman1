package com.example.sysman.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class City {
    private Long id;
    private String code;
    private String name;
    private Department department;
}
