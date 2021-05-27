package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.UserTypeDTO;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserTypeImpl implements UserTypeService {
    @Autowired
    UserTypeRepository userTypeRepository;

    @Override
    public List<UserTypeDTO> findAll() {
        List<UserType> userTypes = userTypeRepository.findAll();
        List<UserTypeDTO> userTypeDTOS = new ArrayList<>();
        for (UserType userType : userTypes) {
            userTypeDTOS.add(new UserTypeDTO(userType));
        }
        return userTypeDTOS;
    }

    @Override
    public UserTypeDTO findById(int id) {
        return new UserTypeDTO(userTypeRepository.findById(id).get());
    }

    @Override
    public UserTypeDTO save(UserType userType) {
        userTypeRepository.save(userType);
        return new UserTypeDTO(userType);
    }

    @Override
    public UserTypeDTO delete(int id) {
        UserType userType = userTypeRepository.findById(id).get();
        if (userType != null) {
            userTypeRepository.delete(userType);
            return new UserTypeDTO(userType);
        }
        return null;
    }
}
