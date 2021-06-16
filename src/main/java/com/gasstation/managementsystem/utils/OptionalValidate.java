package com.gasstation.managementsystem.utils;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OptionalValidate {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final AccountRepository accountRepository;
    private final TankRepository tankRepository;
    private final StationRepository stationRepository;
    private final FuelRepository fuelRepository;

    public Account getAccountById(int id) throws CustomNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (!accountOptional.isPresent()) {
            throw new CustomNotFoundException("Account is not found", "user", "user_table");
        }
        return accountOptional.get();
    }

    public User getUserById(int id) throws CustomNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new CustomNotFoundException("User is not found", "user", "user_table");
        }
        return userOptional.get();
    }

    public UserType getUserTypeById(int id) throws CustomNotFoundException {
        Optional<UserType> userTypeOptional = userTypeRepository.findById(id);
        if (userTypeOptional.isPresent()) {
            return userTypeOptional.get();
        } else {
            throw new CustomNotFoundException("User Type is not exist", "userTypeId", "user_type_table");
        }
    }

    public Tank getTankById(int id) throws CustomNotFoundException {
        Optional<Tank> tankOptional = tankRepository.findById(id);
        if (tankOptional.isPresent()) {
            return tankOptional.get();
        } else {
            throw new CustomNotFoundException("Tank is not exist", "id", "tank_table");
        }
    }

    public Station getStaionById(int id) throws CustomNotFoundException {
        Optional<Station> stationOptional = stationRepository.findById(id);
        if (stationOptional.isPresent()) {
            return stationOptional.get();
        } else {
            throw new CustomNotFoundException("Station is not exist", "id", "tank_table");
        }
    }

    public Fuel getFuelById(int id) throws CustomNotFoundException {
        Optional<Fuel> fuelOptional = fuelRepository.findById(id);
        if (fuelOptional.isPresent()) {
            return fuelOptional.get();
        } else {
            throw new CustomNotFoundException("Fuel is not exist", "id", "fuel_table");
        }
    }

}
