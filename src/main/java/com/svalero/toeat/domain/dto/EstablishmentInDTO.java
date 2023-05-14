package com.svalero.toeat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentInDTO {
    private String name;
    private String description;
    private String adress;
    private double latitude;
    private double longitude;
}
