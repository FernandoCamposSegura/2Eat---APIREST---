package com.svalero.toeat.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svalero.toeat.domain.User;
import com.svalero.toeat.domain.dto.UserInDTO;
import com.svalero.toeat.exception.NotFoundException;
import com.svalero.toeat.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() { return userRepository.findAll(); }

    @Override
    public User getUserById(long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));
    }

    @Override
    public User addUser(UserInDTO userInDTO) {
        User user = new User();

        modelMapper.map(userInDTO, user);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) throws NotFoundException {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(new User()));
        userRepository.delete(user);
    }

    @Override
    public User modifyUser(long id, UserInDTO userInDTO) throws NotFoundException {
        User userModified = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(new User()));
        
        modelMapper.map(userInDTO, userModified);

        return userRepository.save(userModified);
    }
    
}
