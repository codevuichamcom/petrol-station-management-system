package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public UserDTO findById(int id);

    public UserDTO save(User user);

    public UserDTO delete(int id);

    public UserDTO findByUserName(String username);

    public List<UserDTO> findByUserTypeId(int typeId);
}
