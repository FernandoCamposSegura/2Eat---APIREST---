package com.svalero.toeat.domain.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInDTO {
    private int rating;
    private String message;
    private Date datePost;
    private long user_id;
    private long establishment_id;
}
