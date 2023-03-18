package com.svalero.toeat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.svalero.toeat.domain.Comment;
import com.svalero.toeat.domain.dto.CommentInDTO;
import com.svalero.toeat.exception.ErrorMessage;
import com.svalero.toeat.exception.NotFoundException;
import com.svalero.toeat.service.CommentService;
import org.springframework.validation.FieldError;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id) throws NotFoundException {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @GetMapping("/comments/{establishment_id}/establishment")
    public ResponseEntity<List<Comment>> getCommentsByEstablishment(@PathVariable long establishment_id) throws NotFoundException {
        return new ResponseEntity<>(commentService.getCommentsByEstablishment(establishment_id), HttpStatus.OK);
    }

    // @GetMapping("/comments/{establishment_id}")
    // public ResponseEntity<List<Integer>> getRatingsByEstablishment(@PathVariable long establishment_id) throws NotFoundException {
    //     return new ResponseEntity<>(commentService.getRatingsByEstablishment(establishment_id), HttpStatus.OK);
    // }

    @PostMapping("/comments")
    public ResponseEntity<Comment> addComment(@RequestBody CommentInDTO commentInDTO) throws NotFoundException {
        Comment comment = commentService.addComment(commentInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) throws NotFoundException {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> modifyComment(@PathVariable long id, @RequestBody CommentInDTO commentInDTO) throws NotFoundException{
        Comment comment = commentService.modifyComment(id, commentInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException nfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, nfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
