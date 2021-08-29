package com.gasstation.managementsystem;

import com.gasstation.managementsystem.entity.PumpShift;
import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.WorkSchedule;
import com.gasstation.managementsystem.repository.PumpRepository;
import com.gasstation.managementsystem.repository.PumpShiftRepository;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.repository.WorkScheduleRepository;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class App {
    @Autowired
    PumpRepository pumpRepository;
    @Autowired
    ShiftRepository shiftRepository;
    @Autowired
    PumpShiftRepository pumpShiftRepository;
    @Autowired
    WorkScheduleRepository workScheduleRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Scheduled(cron = "${cron.expression}")
    public void createPumpShiftForAllPump() {
        List<PumpShift> pumpShiftList = new ArrayList<>();
        List<WorkSchedule> workScheduleList = workScheduleRepository.findAll();
        long currentDate = DateTimeHelper.getCurrentDate();
        for (WorkSchedule workSchedule : workScheduleList) {
            if (workSchedule.getStartDate() <= currentDate && currentDate <= workSchedule.getEndDate()) {
                Shift shift = workSchedule.getShift();
                Station station = shift.getStation();
                pumpRepository.findAllByStationId(station.getId(), Sort.by(Sort.Direction.ASC, "id")).forEach(pump -> {
                    pumpShiftList.add(PumpShift.builder().createdDate(currentDate).shift(shift).pump(pump).build());
                });
            }
        }
        pumpShiftRepository.saveAll(pumpShiftList);
    }
}
