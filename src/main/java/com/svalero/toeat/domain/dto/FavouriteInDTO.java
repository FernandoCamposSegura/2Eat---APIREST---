package com.svalero.toeat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteInDTO {
    private int user_id;
    private int establishment_id;
}
