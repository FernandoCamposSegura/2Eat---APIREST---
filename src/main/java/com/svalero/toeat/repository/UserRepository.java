package com.svalero.toeat.repository;

import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
}
