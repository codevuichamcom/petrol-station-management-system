package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;

import java.util.HashMap;

public interface UserTypeService {

    HashMap<String, Object> findAll();

    UserTypeDTO findById(int id) throws CustomNotFoundException;

    UserTypeDTO save(UserType userType);

    UserTypeDTO delete(int id) throws CustomNotFoundException;
}
