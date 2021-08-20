package com.gasstation.managementsystem;

import com.gasstation.managementsystem.entity.PumpShift;
import com.gasstation.managementsystem.repository.PumpRepository;
import com.gasstation.managementsystem.repository.PumpShiftRepository;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;

@SpringBootApplication
@EnableScheduling
public class App {
    @Autowired
    PumpRepository pumpRepository;
    @Autowired
    ShiftRepository shiftRepository;
    @Autowired
    PumpShiftRepository pumpShiftRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Scheduled(cron = "0 30 1 * * ?")
    public void createPumpShiftForAllPump() {
        ArrayList<PumpShift> pumpShifts = new ArrayList<>();
        pumpRepository.findAll().forEach(pump -> {
            int stationId = pump.getTank().getStation().getId();
            shiftRepository.findAllShiftByStationId(stationId).forEach(shift -> {
                PumpShift pumpShift = PumpShift.builder().
                        createdDate(DateTimeHelper.getCurrentDate())
                        .shift(shift)
                        .pump(pump).build();
                pumpShifts.add(pumpShift);
            });
        });
        pumpShiftRepository.saveAll(pumpShifts);
    }

}
