package com.svalero.toeat.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svalero.toeat.domain.Establishment;
import com.svalero.toeat.domain.dto.EstablishmentInDTO;
import com.svalero.toeat.exception.NotFoundException;
import com.svalero.toeat.repository.EstablishmentRepository;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Override
    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    @Override
    public Establishment getEstablishmentById(long id) throws NotFoundException {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Establishment()));
    }

    @Override
    public Establishment addEstablishment(EstablishmentInDTO establishmentInDTO) {
        Establishment establishment = new Establishment();

        modelMapper.map(establishmentInDTO, establishment);

        return establishmentRepository.save(establishment);
    }

    @Override
    public void deleteEstablishment(long id) throws NotFoundException {
        Establishment establishment = establishmentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(new Establishment()));
        
        establishmentRepository.delete(establishment);
    }

    @Override
    public Establishment modifyEstablishment(long id, EstablishmentInDTO establishmentInDTO) throws NotFoundException {
        Establishment establishmentModified = establishmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Establishment()));
        
        modelMapper.map(establishmentInDTO, establishmentModified);

        return establishmentRepository.save(establishmentModified);
    }
    
}
