package com.svalero.toeat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findAll();
}
