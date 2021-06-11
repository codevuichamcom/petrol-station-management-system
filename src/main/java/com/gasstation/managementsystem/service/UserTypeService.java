package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface UserTypeService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public HashMap<String, Object> findAll();

    public UserTypeDTO findById(int id);

    public UserTypeDTO save(UserType userType);

    public UserTypeDTO delete(int id);
}
