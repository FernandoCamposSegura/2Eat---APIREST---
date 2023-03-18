package com.svalero.toeat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;

import com.svalero.toeat.domain.Favourite;
import com.svalero.toeat.domain.dto.FavouriteInDTO;
import com.svalero.toeat.exception.ErrorMessage;
import com.svalero.toeat.exception.NotFoundException;
import com.svalero.toeat.service.FavouriteService;

@RestController
public class FavouriteController {
    @Autowired
    private FavouriteService favouriteService;

    @GetMapping("/favourites")
    public ResponseEntity<List<Favourite>> getFavourites() {
        return new ResponseEntity<>(favouriteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/favourites/{id}")
    public ResponseEntity<Favourite> getFavouriteById(@PathVariable long id) throws NotFoundException {
        return new ResponseEntity<>(favouriteService.getFavouriteById(id), HttpStatus.OK);
    }

    @PostMapping("/favourites")
    public ResponseEntity<Favourite> addFavourite(@RequestBody FavouriteInDTO favouriteInDTO) throws NotFoundException{
        Favourite favourite = favouriteService.addFavourite(favouriteInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(favourite);
    }

    @DeleteMapping("/favourites/{id}")
    public ResponseEntity<Void> deleteFavourite(@PathVariable long id) throws NotFoundException {
        favouriteService.deleteFavourite(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/favourites/{id}")
    public ResponseEntity<Favourite> modifyfavourite(@PathVariable long id, @RequestBody FavouriteInDTO favouriteInDTO) throws NotFoundException{
        Favourite favourite = favouriteService.modifyFavourite(id, favouriteInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(favourite);
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
