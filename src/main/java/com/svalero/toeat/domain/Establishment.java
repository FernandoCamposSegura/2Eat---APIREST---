package com.svalero.toeat.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "description is required")
    private String description;

    @Column
    @NotNull
    @NotBlank(message = "adress is required")
    private String adress;
    
    @Column
    @NotNull
    @NotBlank(message = "latitude is required")
    private double latitude;

    @Column
    @NotNull
    @NotBlank(message = "longitude is required")
    private double longitude;

    @OneToMany(mappedBy = "establishment")
    @JsonBackReference(value = "establishment_comment")
    private List<Comment> comments;

    @OneToMany(mappedBy = "establishment")
    @JsonBackReference(value = "establishment_favourite")
    private List<Favourite> favourites;
}
