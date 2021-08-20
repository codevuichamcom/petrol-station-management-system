package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.model.mapper.UserMapper;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.service.UserService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

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
        username = StringUtils.trim(username);
        identityCardNumber = StringUtils.trim(identityCardNumber);
        phone = StringUtils.trim(phone);
        email = StringUtils.trim(email);
        User userDuplicate;
        if (username != null) {
            userDuplicate = userRepository.findByUsername(username);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException(CustomError.builder().code("duplicate").field("username").message("User name field").build());
            }
        }
        if (identityCardNumber != null) {
            userDuplicate = userRepository.findByIdentityCardNumber(identityCardNumber);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException(CustomError.builder().code("duplicate")
                        .field("identityCardNumber").message("Duplicate field").build());
            }
        }
        if (phone != null) {
            userDuplicate = userRepository.findByPhone(phone);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException(CustomError.builder()
                        .code("duplicate").field("phone").message("Duplicate field").build());
            }
        }

        if (email != null) {
            userDuplicate = userRepository.findByEmail(email);
            if (userDuplicate != null) {
                throw new CustomDuplicateFieldException(CustomError.builder()
                        .code("duplicate").field("email").message("Duplicate field").build());
            }
        }
    }


    @Override
    public HashMap<String, Object> findAll() {
        return listUserToMap(userRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    public UserDTO findById(int id) throws CustomNotFoundException {
        return UserMapper.toUserDTO(optionalValidate.getUserById(id));
    }

    @Override
    public UserDTO create(UserDTOCreate userDTOCreate) throws CustomDuplicateFieldException, CustomBadRequestException, CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        if (userType.getId() == UserType.OWNER && userDTOCreate.getUserTypeId() != UserType.CUSTOMER) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("not.match.type").field("userType").message("You only create user with type Customer").build());
        }
        if (userDTOCreate.getUserTypeId() == UserType.ADMIN) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("not.match.type").field("userType").message("Can't create user with type Admin").build());
        }
        checkDuplicateField(userDTOCreate.getUsername(), userDTOCreate.getIdentityCardNumber(), userDTOCreate.getPhone(), userDTOCreate.getEmail());
        User user = UserMapper.toUser(userDTOCreate);
        user.setUserType(optionalValidate.getUserTypeById(userDTOCreate.getUserTypeId()));
        user.setPassword(bcryptEncoder.encode(userDTOCreate.getPassword()));
        trimString(user);
        user = userRepository.save(user);
        return UserMapper.toUserDTO(user);
    }

    private void trimString(User user) {
        user.setUsername(StringUtils.trim(user.getUsername()));
        user.setName(StringUtils.trim(user.getName()));
        user.setAddress(StringUtils.trim(user.getAddress()));
        user.setEmail(StringUtils.trim(user.getEmail()));
        user.setNote(StringUtils.trim(user.getNote()));
    }

    @Override
    public UserDTO update(int id, UserDTOUpdate userDTOUpdate) throws CustomDuplicateFieldException, CustomBadRequestException, CustomNotFoundException {

        User oldUser = optionalValidate.getUserById(id);
        String identityCardNumber = StringUtils.trim(userDTOUpdate.getIdentityCardNumber());
        if (identityCardNumber != null && identityCardNumber.equals(oldUser.getIdentityCardNumber())) {
            identityCardNumber = null;
        }
        String phone = StringUtils.trim(userDTOUpdate.getPhone());
        if (phone != null && phone.trim().equals(oldUser.getPhone())) {
            phone = null;
        }
        String email = StringUtils.trim(userDTOUpdate.getEmail());
        if (email == null) {
            oldUser.setEmail(null);
        } else if (email.equals(oldUser.getEmail())) {
            email = null;
        }
        checkDuplicateField(null, identityCardNumber, phone, email);

        UserMapper.copyToUser(oldUser, userDTOUpdate);
        if (userDTOUpdate.getPassword() != null) {
            oldUser.setPassword(bcryptEncoder.encode(userDTOUpdate.getPassword()));
        }
        trimString(oldUser);
        oldUser = userRepository.save(oldUser);
        return UserMapper.toUserDTO(oldUser);
    }

    @Override
    public UserDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        User user = optionalValidate.getUserById(id);
        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("user_id")
                    .message("User in use").build());
        }
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
