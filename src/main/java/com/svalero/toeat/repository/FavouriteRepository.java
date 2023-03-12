package com.svalero.toeat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.Favourite;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Long> {
    public List<Favourite> findAll();
}
