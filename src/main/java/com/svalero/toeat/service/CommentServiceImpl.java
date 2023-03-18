package com.svalero.toeat.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svalero.toeat.domain.Comment;
import com.svalero.toeat.domain.Establishment;
import com.svalero.toeat.domain.User;
import com.svalero.toeat.domain.dto.CommentInDTO;
import com.svalero.toeat.exception.NotFoundException;
import com.svalero.toeat.repository.CommentRepository;
import com.svalero.toeat.repository.EstablishmentRepository;
import com.svalero.toeat.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    EstablishmentRepository establishmentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(long id) throws NotFoundException {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Comment()));
    }

    @Override
    public List<Comment> getCommentsByEstablishment(long establishment_id) throws NotFoundException {
         Establishment establishment = establishmentRepository.findById(establishment_id)
                .orElseThrow(() -> new NotFoundException(new Establishment()));

        return commentRepository.findCommentsByEstablishmentId(establishment.getId());
    }

    @Override
    public Comment addComment(CommentInDTO commentInDTO) throws NotFoundException {
        Comment comment = new Comment();

        User user = userRepository.findById(commentInDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException(new User()));

        Establishment establishment = establishmentRepository.findById(commentInDTO.getEstablishment_id())
                .orElseThrow(() -> new NotFoundException(new Establishment()));

        modelMapper.map(commentInDTO, comment);
        comment.setUser(user);
        comment.setEstablishment(establishment);
        
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) throws NotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Comment()));

        commentRepository.delete(comment);
    }

    @Override
    public Comment modifyComment(long id, CommentInDTO commentInDTO) throws NotFoundException {
        Comment commentModified = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Comment()));

        User user = userRepository.findById(commentInDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException(new User()));

        Establishment establishment = establishmentRepository.findById(commentInDTO.getEstablishment_id())
                .orElseThrow(() -> new NotFoundException(new Establishment()));

        modelMapper.map(commentInDTO, commentModified);
        commentModified.setUser(user);
        commentModified.setEstablishment(establishment);

        return commentRepository.save(commentModified);
    }
}
