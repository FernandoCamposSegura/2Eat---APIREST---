package com.svalero.toeat.service;

import java.util.List;

import com.svalero.toeat.domain.Favourite;
import com.svalero.toeat.domain.dto.FavouriteInDTO;
import com.svalero.toeat.exception.NotFoundException;

public interface FavouriteService {
    List<Favourite> findAll();

    Favourite addFavourite(FavouriteInDTO favouriteInDTO) throws NotFoundException;

    void deleteFavourite(long id) throws NotFoundException;

    Favourite modifyFavourite(long id, FavouriteInDTO favouriteInDTO) throws NotFoundException;
}
