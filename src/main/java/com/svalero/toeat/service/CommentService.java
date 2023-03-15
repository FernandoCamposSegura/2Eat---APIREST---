package com.svalero.toeat.service;

import java.util.List;

import com.svalero.toeat.domain.Comment;
import com.svalero.toeat.domain.dto.CommentInDTO;
import com.svalero.toeat.exception.NotFoundException;

public interface CommentService {
    List<Comment> findAll();

    Comment addComment(CommentInDTO commentInDTO) throws NotFoundException;

    void deleteComment(long id) throws NotFoundException;

    Comment modifyComment(long id, CommentInDTO userInDTO) throws NotFoundException;
}
