package com.svalero.toeat.service;

import java.util.List;

import com.svalero.toeat.domain.Comment;
import com.svalero.toeat.domain.Establishment;
import com.svalero.toeat.domain.dto.EstablishmentInDTO;
import com.svalero.toeat.exception.NotFoundException;

public interface EstablishmentService {
    List<Establishment> findAll();

    Establishment getEstablishmentById(long id) throws NotFoundException;

    List<Establishment> getEstablishmentsByFilter(String filter);

    List<Comment> getCommentByEstablishmentId(long id) throws NotFoundException;

    Establishment addEstablishment(EstablishmentInDTO establishmentInDTO);

    void deleteEstablishment(long id) throws NotFoundException;

    Establishment modifyEstablishment(long id, EstablishmentInDTO establishmentInDTO) throws NotFoundException;
}
