package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface UserService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public HashMap<String, Object> findAll();

    public UserDTO findById(int id);

    public UserDTO save(User user);

    public UserDTO delete(int id);

    public UserDTO findByUserName(String username);

    public HashMap<String, Object> findByUserTypeId(int typeId);
}
