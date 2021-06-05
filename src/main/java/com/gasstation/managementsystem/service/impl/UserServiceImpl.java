package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.UserDTO;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import com.gasstation.managementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserTypeRepository userTypeRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder bcryptEncoder;

    private HashMap<String, Object> listUserToMap(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user));
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
        return new UserDTO(userRepository.findById(id).get());
    }

    @Override
    public UserDTO save(User user) {
        if (user.getUserType() != null) {
            UserType userType = userTypeRepository.findById(user.getUserType().getId()).get();
            user.setUserType(userType);
        }
        user = userRepository.save(user);
        Account account = user.getAccount();
        if (account != null) {
            account.setUserInfo(user);
            account.setPassword(bcryptEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        }
        return new UserDTO(user);
    }

    @Override
    public UserDTO delete(int id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            userRepository.delete(user);
            return new UserDTO(user);
        }
        return null;
    }

    @Override
    public UserDTO findByUserName(String username) {
        return new UserDTO(userRepository.findByUsername(username));
    }

    @Override
    public HashMap<String, Object> findByUserTypeId(int typeId) {
        List<User> users = userRepository.findByUserTypeId(typeId);
        return listUserToMap(users);
    }
}
