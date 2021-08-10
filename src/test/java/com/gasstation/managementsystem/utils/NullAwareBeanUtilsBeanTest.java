package com.gasstation.managementsystem.utils;

import com.gasstation.managementsystem.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;

class NullAwareBeanUtilsBeanTest {
    @InjectMocks
    private NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void copyProperty() throws InvocationTargetException, IllegalAccessException {
        User user1 = User.builder().id(1).name("User1").gender(true).address("Ha Noi").build();
        User user2 = User.builder().id(1).name("User2").build();
        nullAwareBeanUtilsBean.copyProperty(user2,"copyUser",user1);
    }
}