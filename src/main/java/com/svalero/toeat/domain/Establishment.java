package com.svalero.toeat.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "establishments")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @NotBlank(message = "name is required")
    private String name;

    @Column
    @NotNull
    @NotBlank(message = "adress is required")
    private String description;

    @Column
    @NotNull
    @NotBlank(message = "adress is required")
    private String adress;
    
    @Column
    @NotNull
    @NotBlank(message = "latitude is required")
    private long latitude;

    @Column
    @NotNull
    @NotBlank(message = "longitude is required")
    private long longitude;
}
