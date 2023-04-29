package com.svalero.toeat.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @NotBlank(message = "rating is required")
    private double rating;

    @Column
    @NotNull
    @NotBlank(message = "message is required")
    private String message;

    @Column
    @NotNull
    @NotBlank(message = "date is required")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datePost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}
