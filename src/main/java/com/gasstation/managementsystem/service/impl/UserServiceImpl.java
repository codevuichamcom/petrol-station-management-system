package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.model.mapper.AccountMapper;
import com.gasstation.managementsystem.model.mapper.UserMapper;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final AccountRepository accountRepository;

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
    public UserDTO findById(int id) {
        return UserMapper.toUserDTO(userRepository.findById(id).get());
    }

    @Override
    public UserDTO create(UserDTOCreate userDTOCreate) {
        User user = UserMapper.toUser(userDTOCreate);
        UserType userType = userTypeRepository.findById(userDTOCreate.getUserTypeId()).get();
        user.setUserType(userType);
        user.setAccount(AccountMapper.toAccount(userDTOCreate.getAccount()));
        user = userRepository.save(user);
        Account account = user.getAccount();
        if (account != null) {
            account.setUserInfo(user);
            accountRepository.save(account);
        }
        return UserMapper.toUserDTO(user);
    }

    @Override
    public UserDTO update(int id, UserDTOUpdate userDTOUpdate) {
        User user = UserMapper.toUser(userDTOUpdate);
        user.setId(id);
        UserType userType = userTypeRepository.findById(userDTOUpdate.getUserTypeId()).get();
        user.setUserType(userType);
        user.setAccount(AccountMapper.toAccount(userDTOUpdate.getAccount()));
        user = userRepository.save(user);
        return UserMapper.toUserDTO(user);
    }

    @Override
    public UserDTO delete(int id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            userRepository.delete(user);
            return UserMapper.toUserDTO(user);
        }
        return null;
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
