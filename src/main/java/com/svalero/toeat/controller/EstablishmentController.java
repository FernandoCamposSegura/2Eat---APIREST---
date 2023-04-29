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

import com.svalero.toeat.domain.Establishment;
import com.svalero.toeat.domain.dto.EstablishmentInDTO;
import com.svalero.toeat.exception.ErrorMessage;
import com.svalero.toeat.exception.NotFoundException;
import com.svalero.toeat.service.EstablishmentService;

@RestController
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping("/establishments")
    public ResponseEntity<List<Establishment>> getEstablishments(@RequestParam(name = "name", defaultValue = "", required = false) String name) {
        if(!name.equals(""))
            return new ResponseEntity<>(establishmentService.getEstablishmentsByName(name), HttpStatus.OK);
        return new ResponseEntity<>(establishmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/establishments/{id}")
    public ResponseEntity<Establishment> getEstablishmentById(@PathVariable long id) throws NotFoundException {
        return new ResponseEntity<>(establishmentService.getEstablishmentById(id), HttpStatus.OK);
    }

    @PostMapping("/establishments")
    public ResponseEntity<Establishment> addEstablishment(@RequestBody EstablishmentInDTO establishmentInDTO) {
        Establishment establishment = establishmentService.addEstablishment(establishmentInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(establishment);
    }

    @DeleteMapping("/establishments/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable long id) throws NotFoundException {
        establishmentService.deleteEstablishment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/establishments/{id}")
    public ResponseEntity<Establishment> modifyEstablishment(@PathVariable long id, @RequestBody EstablishmentInDTO establishmentInDTO) throws NotFoundException{
        Establishment establishment = establishmentService.modifyEstablishment(id, establishmentInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(establishment);
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
