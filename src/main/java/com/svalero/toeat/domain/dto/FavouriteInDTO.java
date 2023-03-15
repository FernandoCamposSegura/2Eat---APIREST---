package com.svalero.toeat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteInDTO {
    private long user_id;
    private long establishment_id;
}
