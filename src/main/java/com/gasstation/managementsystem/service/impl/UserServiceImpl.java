package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.UserDTO;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public UserDTO findById(int id) {
        return new UserDTO(userRepository.findById(id).get());
    }

    @Override
    public UserDTO save(User user) {
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Override
    public UserDTO delete(int id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            userRepository.delete(user);
            return new UserDTO(user);
        }
        return null;
    }
}
