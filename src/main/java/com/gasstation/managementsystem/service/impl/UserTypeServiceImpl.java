package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.model.mapper.UserTypeMapper;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.service.UserTypeService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listUserTypeToMap(List<UserType> userTypes) {
        List<UserTypeDTO> userTypeDTOS = new ArrayList<>();
        for (UserType userType : userTypes) {
            userTypeDTOS.add(UserTypeMapper.toUserTypeDTO(userType));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userTypeDTOS);
        return map;
    }


    @Override
    public HashMap<String, Object> findAll() {
        List<UserType> userTypes = userTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return listUserTypeToMap(userTypes);
    }


    @Override
    public UserTypeDTO findById(int id) throws CustomNotFoundException {
        return UserTypeMapper.toUserTypeDTO(optionalValidate.getUserTypeById(id));
    }

    @Override
    public UserTypeDTO save(UserType userType) {
        userTypeRepository.save(userType);
        return UserTypeMapper.toUserTypeDTO(userType);
    }

    @Override
    public UserTypeDTO delete(int id) throws CustomNotFoundException {
        UserType userType = optionalValidate.getUserTypeById(id);
        userTypeRepository.delete(userType);
        return UserTypeMapper.toUserTypeDTO(userType);
    }
}
