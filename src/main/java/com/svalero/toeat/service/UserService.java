package com.svalero.toeat.service;

import java.util.List;

import com.svalero.toeat.domain.User;
import com.svalero.toeat.domain.dto.UserInDTO;
import com.svalero.toeat.exception.NotFoundException;

public interface UserService {
    List<User> findAll();

    User getUserById(long id) throws NotFoundException;

    User addUser(UserInDTO userInDTO);

    void deleteUser(long id) throws NotFoundException;

    User modifyUser(long id, UserInDTO userInDTO) throws NotFoundException;
}
