package com.gasstation.managementsystem.utils;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final UserRepository userRepository;

    public User getUserLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username);
        }
        return null;
    }

    public UserType getUserTypeOfUserLogin() {
        User user = getUserLogin();
        return (user != null) ? user.getUserType() : null;
    }

    public boolean isAdmin() {
        return getUserTypeOfUserLogin().getId() == UserType.ADMIN;
    }

    public boolean isOwner() {
        return getUserTypeOfUserLogin().getId() == UserType.OWNER;
    }

    public List<Integer> getListStationIdOfOwner(User owner) {
        List<Integer> stationIds = new ArrayList<>();
        for (Station station : owner.getStationList()) {
            stationIds.add(station.getId());
        }
        return stationIds;
    }
}
