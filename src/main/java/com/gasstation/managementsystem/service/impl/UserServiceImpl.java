package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.model.mapper.UserMapper;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.service.UserService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OptionalValidate optionalValidate;

    private final PasswordEncoder bcryptEncoder;

    private HashMap<String, Object> listUserToMap(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(UserMapper.toUserDTO(user));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userDTOS);
        return map;
    }


    public void checkDuplicateField(String username, String identityCardNumber, String phone, String email) throws CustomDuplicateFieldException {
        User userDuplicate;
        if (username != null) {
            userDuplicate = userRepository.findByUsername(username);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException("User name field", "username", null);
            }
        }
        if (identityCardNumber != null) {
            userDuplicate = userRepository.findByIdentityCardNumber(identityCardNumber);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException("Duplicate field", "indentityCardNumber", null);
            }
        }
        if (phone != null) {
            userDuplicate = userRepository.findByPhone(phone);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException("Duplicate field", "phone", null);
            }
        }

        if (email != null) {
            userDuplicate = userRepository.findByEmail(email);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException("Duplicate field", "email", null);
            }
        }
    }


    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        HashMap<String, Object> map = listUserToMap(users.getContent());
        map.put("totalElement", users.getTotalElements());
        map.put("totalPage", users.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        List<User> users = userRepository.findAll();
        return listUserToMap(users);
    }

    @Override
    public UserDTO findById(int id) throws CustomNotFoundException {
        return UserMapper.toUserDTO(optionalValidate.getUserById(id));
    }

    @Override
    public UserDTO create(UserDTOCreate userDTOCreate) throws CustomDuplicateFieldException, CustomBadRequestException, CustomNotFoundException {
        if (userDTOCreate.getUserTypeId() == UserType.ADMIN) {
            throw new CustomBadRequestException("Can't create user with type Admin", "userType", null);
        }
        checkDuplicateField(userDTOCreate.getUsername(), userDTOCreate.getIdentityCardNumber(), userDTOCreate.getPhone(), userDTOCreate.getEmail());
        User user = UserMapper.toUser(userDTOCreate);
        UserType userType = optionalValidate.getUserTypeById(userDTOCreate.getUserTypeId());
        user.setUserType(userType);
        user.setPassword(bcryptEncoder.encode(userDTOCreate.getPassword()));
        user = userRepository.save(user);
        return UserMapper.toUserDTO(user);
    }

    @Override
    public UserDTO update(int id, UserDTOUpdate userDTOUpdate) throws CustomDuplicateFieldException, CustomBadRequestException, CustomNotFoundException {

        User oldUser = optionalValidate.getUserById(id);
        String identityCardNumber = userDTOUpdate.getIdentityCardNumber();
        if (identityCardNumber != null && identityCardNumber.equals(oldUser.getIdentityCardNumber())) {
            identityCardNumber = null;
        }
        String phone = userDTOUpdate.getPhone();
        if (phone != null && phone.equals(oldUser.getPhone())) {
            phone = null;
        }
        String email = userDTOUpdate.getEmail();
        if (email != null && email.equals(oldUser.getEmail())) {
            email = null;
        }
        checkDuplicateField(null, identityCardNumber, phone, email);

        UserMapper.copyToUser(oldUser, userDTOUpdate);
        if (userDTOUpdate.getUserTypeId() != null) {
            if (userDTOUpdate.getUserTypeId() == UserType.ADMIN) {
                throw new CustomBadRequestException("Can't create user with type Admin", "userType", null);
            }
            UserType userType = optionalValidate.getUserTypeById(userDTOUpdate.getUserTypeId());
            oldUser.setUserType(userType);
        }
        if (userDTOUpdate.getPassword() != null) {
            oldUser.setPassword(bcryptEncoder.encode(userDTOUpdate.getPassword()));
        }
        oldUser = userRepository.save(oldUser);
        return UserMapper.toUserDTO(oldUser);
    }

    @Override
    public UserDTO delete(int id) throws CustomNotFoundException {
        User user = optionalValidate.getUserById(id);
        userRepository.delete(user);
        return UserMapper.toUserDTO(user);
    }

    @Override
    public UserDTO findByUserName(String username) {
        return UserMapper.toUserDTO(userRepository.findByUsername(username));
    }

    @Override
    public HashMap<String, Object> findByUserTypeId(int typeId) {
        List<User> users = userRepository.findByUserTypeId(typeId);
        return listUserToMap(users);
    }
}
