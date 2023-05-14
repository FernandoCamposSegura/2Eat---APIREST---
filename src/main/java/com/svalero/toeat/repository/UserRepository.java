package com.svalero.toeat.repository;

import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();

    @Query( value = "SELECT * FROM users WHERE username=? AND password=?", nativeQuery = true)
    User findUserByUsernameAndPassword(String username, String password);
}
