package com.svalero.toeat.domain.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInDTO {
    private double rating;
    private String message;
    private LocalDate datePost;
    private long user_id;
    private long establishment_id;
}
