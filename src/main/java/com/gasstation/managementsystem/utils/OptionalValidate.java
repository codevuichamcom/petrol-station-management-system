package com.gasstation.managementsystem.utils;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OptionalValidate {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final TankRepository tankRepository;
    private final StationRepository stationRepository;
    private final FuelRepository fuelRepository;
    private final PumpRepository pumpRepository;
    private final TransactionRepository transactionRepository;
    private final ShiftRepository shiftRepository;
    private final CardRepository cardRepository;
    private final ApiRepository apiRepository;
    private final SupplierRepository supplierRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkScheduleRepository workScheduleRepository;
    private final FuelImportRepository fuelImportRepository;
    private final ExpenseRepository expenseRepository;
    private final PumpShiftRepository pumpShiftRepository;
    private final ReceiptRepository receiptRepository;
    private final DebtRepository debtRepository;


    public User getUserById(int id) throws CustomNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("User is not found").table("user_table").build());
        }
        return userOptional.get();
    }

    public UserType getUserTypeById(int id) throws CustomNotFoundException {
        Optional<UserType> userTypeOptional = userTypeRepository.findById(id);
        if (userTypeOptional.isPresent()) {
            return userTypeOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("User Type is not exist").table("user_type_table").build());
        }
    }

    public Tank getTankById(int id) throws CustomNotFoundException {
        Optional<Tank> tankOptional = tankRepository.findById(id);
        if (tankOptional.isPresent()) {
            return tankOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Tank is not exist").table("tank_table").build());
        }
    }

    public Station getStationById(int id) throws CustomNotFoundException {
        Optional<Station> stationOptional = stationRepository.findById(id);
        if (stationOptional.isPresent()) {
            return stationOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Station is not exist").table("station_table").build());
        }
    }

    public Fuel getFuelById(int id) throws CustomNotFoundException {
        Optional<Fuel> fuelOptional = fuelRepository.findById(id);
        if (fuelOptional.isPresent()) {
            return fuelOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Fuel is not exist").table("fuel_table").build());
        }
    }

    public Pump getPumpById(int id) throws CustomNotFoundException {
        Optional<Pump> pumpOptional = pumpRepository.findById(id);
        if (pumpOptional.isPresent()) {
            return pumpOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Pump is not exist").table("pump_table").build());
        }
    }

    public Transaction getTransactionById(int id) throws CustomNotFoundException {
        Optional<Transaction> pumpCodeOptional = transactionRepository.findById(id);
        if (pumpCodeOptional.isPresent()) {
            return pumpCodeOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Transaction is not exist").table("transaction_table").build());
        }
    }

    public Shift getShiftById(int id) throws CustomNotFoundException {
        Optional<Shift> shiftOptional = shiftRepository.findById(id);
        if (shiftOptional.isPresent()) {
            return shiftOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Shift is not exist").table("shift_table").build());
        }
    }

    public Card getCardById(UUID id) throws CustomNotFoundException {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()) {
            return cardOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Card is not exist").table("card_table").build());
        }
    }

    public Api getApiById(int id) throws CustomNotFoundException {
        Optional<Api> apiOptional = apiRepository.findById(id);
        if (apiOptional.isPresent()) {
            return apiOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Api is not exist").table("api_table").build());
        }
    }

    public Supplier getSupplierById(int id) throws CustomNotFoundException {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            return supplierOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Supplier is not exist").table("supplier_table").build());
        }
    }

    public Employee getEmployeeById(int id) throws CustomNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Employee is not exist").table("employee_table").build());
        }
    }

    public WorkSchedule getWorkScheduleById(int id) throws CustomNotFoundException {
        Optional<WorkSchedule> workScheduleOptional = workScheduleRepository.findById(id);
        if (workScheduleOptional.isPresent()) {
            return workScheduleOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").message("Work schedule is not exist")
                    .table("work_schedule_tbl").build());
        }
    }

    public FuelImport getFuelImportById(int id) throws CustomNotFoundException {
        Optional<FuelImport> fuelImportOptional = fuelImportRepository.findById(id);
        if (fuelImportOptional.isPresent()) {
            return fuelImportOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Fuel import is not exist").table("fuel_import_table").build());
        }
    }

    public Expense getExpenseById(int id) throws CustomNotFoundException {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        if (expenseOptional.isPresent()) {
            return expenseOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Expense is not exist").table("expense_table").build());
        }
    }

    public PumpShift getPumpShiftById(int id) throws CustomNotFoundException {
        Optional<PumpShift> pumpShiftOptional = pumpShiftRepository.findById(id);
        if (pumpShiftOptional.isPresent()) {
            return pumpShiftOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("Pump shift is not exist").table("pump_shift_table").build());
        }
    }

    public PumpShift getPumpShiftByPumpIdNotClose(int id, long createdDate, long milliSeconds) {
        Optional<PumpShift> pumpShiftOptional = pumpShiftRepository.findByPumpIdNotClose(id, createdDate, milliSeconds);
        return pumpShiftOptional.orElse(null);
    }

    public Receipt getReceiptById(int id) throws CustomNotFoundException {
        Optional<Receipt> receiptOptional = receiptRepository.findById(id);
        if (receiptOptional.isPresent()) {
            return receiptOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("receipt is not exist").table("receipt_table").build());
        }
    }

    public Debt getDebtById(int id) throws CustomNotFoundException {
        Optional<Debt> debtOptional = debtRepository.findById(id);
        if (debtOptional.isPresent()) {
            return debtOptional.get();
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found").field("id").message("debt is not exist").table("debt_table").build());
        }
    }

}
