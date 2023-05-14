package com.svalero.toeat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.Favourite;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Long> {
    public List<Favourite> findAll();

    @Query( value = "SELECT * FROM favourites WHERE user_id=? AND establishment_id=?", nativeQuery = true)
    List<Favourite> findFavouriteByUserIdAndEstablishmentId(long user_id, long establishment_id);
}
