package com.svalero.toeat.repository;

import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.Establishment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {
    public List<Establishment> findAll();
}