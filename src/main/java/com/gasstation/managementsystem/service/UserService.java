package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    public List<UserDTO> findAll();

    public UserDTO findById(int id);

    public UserDTO save(User user);

    public UserDTO delete(int id);
}
