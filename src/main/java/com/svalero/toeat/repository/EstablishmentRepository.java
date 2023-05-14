package com.svalero.toeat.repository;

import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.Establishment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {
    public List<Establishment> findAll();

    @Query( value = "SELECT * FROM establishments WHERE INSTR(name, ?) != 0", nativeQuery = true)
    List<Establishment> findEstablishmentsByFilter(String filter);
}
