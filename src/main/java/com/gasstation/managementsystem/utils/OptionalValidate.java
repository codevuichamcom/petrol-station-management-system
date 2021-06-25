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
    private final TankRepository tankRepository;
    private final StationRepository stationRepository;
    private final FuelRepository fuelRepository;
    private final PumpRepository pumpRepository;
    private final PumpCodeRepository pumpCodeRepository;
    private final ShiftRepository shiftRepository;
    private final CardRepository cardRepository;
    private final ApiRepository apiRepository;


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

    public Station getStationById(int id) throws CustomNotFoundException {
        Optional<Station> stationOptional = stationRepository.findById(id);
        if (stationOptional.isPresent()) {
            return stationOptional.get();
        } else {
            throw new CustomNotFoundException("Station is not exist", "id", "station_table");
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

    public Pump getPumpById(int id) throws CustomNotFoundException {
        Optional<Pump> pumpOptional = pumpRepository.findById(id);
        if (pumpOptional.isPresent()) {
            return pumpOptional.get();
        } else {
            throw new CustomNotFoundException("Pump is not exist", "id", "pump_table");
        }
    }

    public PumpCode getPumpCodeById(int id) throws CustomNotFoundException {
        Optional<PumpCode> pumpCodeOptional = pumpCodeRepository.findById(id);
        if (pumpCodeOptional.isPresent()) {
            return pumpCodeOptional.get();
        } else {
            throw new CustomNotFoundException("Pump Code is not exist", "id", "pump_code_table");
        }
    }

    public Shift getShiftById(int id) throws CustomNotFoundException {
        Optional<Shift> shiftOptional = shiftRepository.findById(id);
        if (shiftOptional.isPresent()) {
            return shiftOptional.get();
        } else {
            throw new CustomNotFoundException("Shift is not exist", "id", "shift_table");
        }
    }

    public Card getCardById(int id) throws CustomNotFoundException {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()) {
            return cardOptional.get();
        } else {
            throw new CustomNotFoundException("Card is not exist", "id", "card_table");
        }
    }

    public Api getApiById(int id) throws CustomNotFoundException {
        Optional<Api> apiOptional = apiRepository.findById(id);
        if (apiOptional.isPresent()) {
            return apiOptional.get();
        } else {
            throw new CustomNotFoundException("Api is not exist", "id", "api_table");
        }
    }

    public Api getApiByApiAndMethod(String api, String method) throws CustomNotFoundException {
        Optional<Api> apiOptional = apiRepository.findByApiAndMethod(api, method);
        if (apiOptional.isPresent()) {
            return apiOptional.get();
        } else {
            throw new CustomNotFoundException("Api and method is not exist", "(api,method)", "api_table");
        }
    }

}
