package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.model.mapper.UserTypeMapper;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserTypeImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;

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
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<UserType> userTypes = userTypeRepository.findAll(pageable);
        HashMap<String, Object> map = listUserTypeToMap(userTypes.getContent());
        map.put("totalElement", userTypes.getTotalElements());
        map.put("totalPage", userTypes.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        List<UserType> userTypes = userTypeRepository.findAll();
        return listUserTypeToMap(userTypes);
    }


    @Override
    public UserTypeDTO findById(int id) {
        return UserTypeMapper.toUserTypeDTO(userTypeRepository.findById(id).get());
    }

    @Override
    public UserTypeDTO save(UserType userType) {
        userTypeRepository.save(userType);
        return UserTypeMapper.toUserTypeDTO(userType);
    }

    @Override
    public UserTypeDTO delete(int id) {
        UserType userType = userTypeRepository.findById(id).get();
        userTypeRepository.delete(userType);
        return UserTypeMapper.toUserTypeDTO(userType);
    }
}
