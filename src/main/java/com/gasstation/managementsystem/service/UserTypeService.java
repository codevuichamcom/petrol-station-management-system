package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.UserTypeDTO;

import java.util.List;

public interface UserTypeService {
    public List<UserTypeDTO> findAll();

    public UserTypeDTO findById(int id);

    public UserTypeDTO save(UserType userType);

    public UserTypeDTO delete(int id);
}
