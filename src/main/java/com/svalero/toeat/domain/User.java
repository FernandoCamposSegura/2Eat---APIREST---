package com.svalero.toeat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @NotBlank(message = "username is required")
    private String username;

    @Column
    @NotNull
    @NotBlank(message = "username is required")
    private String email;

    @Column
    @NotNull
    @NotBlank(message = "password is required")
    private String password;
}