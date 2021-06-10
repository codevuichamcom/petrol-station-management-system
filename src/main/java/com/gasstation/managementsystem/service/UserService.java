package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface UserService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public HashMap<String, Object> findAll();

    public UserDTO findById(int id);

    public UserDTO create(UserDTOCreate userDTOCreate) throws CustomDuplicateFieldException;

    public UserDTO update(int id, UserDTOUpdate userDTOUpdate) throws CustomDuplicateFieldException;

    public UserDTO delete(int id);

    public UserDTO findByUserName(String username);

    public HashMap<String, Object> findByUserTypeId(int typeId);
}
