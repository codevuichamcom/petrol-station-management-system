package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.UserTypeDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface UserTypeService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public UserTypeDTO findById(int id);

    public UserTypeDTO save(UserType userType);

    public UserTypeDTO delete(int id);
}
