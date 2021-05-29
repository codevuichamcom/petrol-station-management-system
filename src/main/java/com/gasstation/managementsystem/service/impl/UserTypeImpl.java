package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.UserTypeDTO;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserTypeImpl implements UserTypeService {
    @Autowired
    UserTypeRepository userTypeRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<UserType> userTypes = userTypeRepository.findAll(pageable);
        List<UserTypeDTO> userTypeDTOS = new ArrayList<>();
        for (UserType supplier : userTypes) {
            userTypeDTOS.add(new UserTypeDTO(supplier));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userTypeDTOS);
        map.put("totalElement", userTypes.getTotalElements());
        map.put("totalPage", userTypes.getTotalPages());
        return map;
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
