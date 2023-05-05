package com.svalero.toeat.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "username is required")
    private String username;

    @Column
    @NotNull
    @NotBlank(message = "email is required")
    private String email;

    @Column
    @NotNull
    @NotBlank(message = "password is required")
    private String password;

    @Column
    @NotNull
    @NotBlank
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_comment")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_favourite")
    private List<Favourite> favourites;
}