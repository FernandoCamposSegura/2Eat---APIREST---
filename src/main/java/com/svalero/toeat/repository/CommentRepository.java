package com.svalero.toeat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.svalero.toeat.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findAll();

    @Query( value = "SELECT * FROM comments WHERE establishment_id=?", nativeQuery = true)
    List<Comment> findCommentsByEstablishmentId(long establishment_id);
}
