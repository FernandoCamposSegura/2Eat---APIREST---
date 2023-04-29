package com.svalero.toeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svalero.toeat.domain.Establishment;
import com.svalero.toeat.domain.Favourite;
import com.svalero.toeat.domain.User;
import com.svalero.toeat.domain.dto.FavouriteInDTO;
import com.svalero.toeat.exception.NotFoundException;
import com.svalero.toeat.repository.EstablishmentRepository;
import com.svalero.toeat.repository.FavouriteRepository;
import com.svalero.toeat.repository.UserRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {

        @Autowired
        FavouriteRepository favouriteRepository;
        @Autowired
        UserRepository userRepository;
        @Autowired
        EstablishmentRepository establishmentRepository;

        @Override
        public List<Favourite> findAll() {
                return favouriteRepository.findAll();
        }

        @Override
        public Favourite getFavouriteById(long id) throws NotFoundException {
                return favouriteRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException(new Favourite()));
        }

        @Override
        public Favourite addFavourite(FavouriteInDTO favouriteInDTO) throws NotFoundException {
                Favourite favourite = new Favourite();

                User user = userRepository.findById(favouriteInDTO.getUser_id())
                                .orElseThrow(() -> new NotFoundException(new User()));

                Establishment establishment = establishmentRepository.findById(favouriteInDTO.getEstablishment_id())
                                .orElseThrow(() -> new NotFoundException(new Establishment()));

                favourite.setUser(user);
                favourite.setEstablishment(establishment);

                return favouriteRepository.save(favourite);
        }

        @Override
        public void deleteFavourite(long id) throws NotFoundException {
                Favourite favourite = favouriteRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException(new Favourite()));
                favouriteRepository.delete(favourite);
        }

        @Override
        public Favourite modifyFavourite(long id, FavouriteInDTO favouriteInDTO) throws NotFoundException {
                Favourite favouriteModified = favouriteRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException(new Favourite()));

                User user = userRepository.findById(favouriteInDTO.getUser_id())
                                .orElseThrow(() -> new NotFoundException(new User()));

                Establishment establishment = establishmentRepository.findById(favouriteInDTO.getEstablishment_id())
                                .orElseThrow(() -> new NotFoundException(new Establishment()));

                favouriteModified.setUser(user);
                favouriteModified.setEstablishment(establishment);

                return favouriteRepository.save(favouriteModified);
        }

        @Override
        public List<Favourite> getFavouriteByUserAndEstablishment(long user_id, long establishment_id)
                        throws NotFoundException {
                User user = userRepository.findById(user_id)
                                .orElseThrow(() -> new NotFoundException(new User()));

                Establishment establishment = establishmentRepository.findById(establishment_id)
                                .orElseThrow(() -> new NotFoundException(new Establishment()));

                return favouriteRepository.findFavouriteByUserIdAndEstablishmentId(user_id, establishment_id);
        }
}
