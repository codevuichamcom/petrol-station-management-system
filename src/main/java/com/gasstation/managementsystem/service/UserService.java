package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface UserService {

    HashMap<String, Object> findAll();

    UserDTO findById(int id) throws CustomNotFoundException;

    UserDTO create(UserDTOCreate userDTOCreate) throws CustomDuplicateFieldException, CustomBadRequestException, CustomNotFoundException;

    UserDTO update(int id, UserDTOUpdate userDTOUpdate) throws CustomDuplicateFieldException, CustomBadRequestException, CustomNotFoundException;

    UserDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException;

    UserDTO findByUserName(String username);

    HashMap<String, Object> findByUserTypeId(int typeId);

}
