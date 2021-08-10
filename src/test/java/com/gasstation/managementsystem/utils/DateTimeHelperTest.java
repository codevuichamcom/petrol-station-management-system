package com.gasstation.managementsystem.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeHelperTest {

    @InjectMocks
    private DateTimeHelper dateTimeHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toUnixTime() {
        long result = 1609459200000L;
        long test = dateTimeHelper.toUnixTime("2021-01-01 07:00:00", "yyyy-MM-dd HH:mm:ss");
        assertEquals(result, test);
    }

    @Test
    void getCurrentUnixTime() {
        Long result = Instant.now().toEpochMilli();
        Long test = dateTimeHelper.getCurrentUnixTime();
        assertEquals(result, test);
    }

    @Test
    void getCurrentDate() {
        Long result = dateTimeHelper.getCurrentDate();
        Long test = dateTimeHelper.getCurrentDate();
        assertEquals(result, test);
    }

    @Test
    void toDateTime() {
        LocalDateTime result = LocalDateTime.of(2021,8,01,00,00);
        LocalDateTime test = dateTimeHelper.toDateTime(1627750800000L);
        assertEquals(result, test);
    }

    /**
     * try
     */
    @Test
    void toDate_UTCID01() throws ParseException {
        Date result = new Date();
        result.setTime(1609434000000L);
        Date test = dateTimeHelper.toDate("2021-01-01", "yyyy-MM-dd");
        assertEquals(result, test);
    }

    /**
     * catch
     */
    @Test
    void toDate_UTCID02() throws ParseException {
        Date result = new Date();
        result.setTime(1609434000000L);
        Date test = dateTimeHelper.toDate("2021-01-01", "dasdasd");
        System.out.println(test);
    }

    @Test
    void formatDate() {
        String result = "2021-01-01";
        Date date = new Date();
        date.setTime(1609434000000L);
        String test = dateTimeHelper.formatDate(date, "yyyy-MM-dd");
        assertEquals(result, test);
    }

    @Test
    void toMilliSecond() {
        Long result = 12345000L;
        Long test = dateTimeHelper.toMilliSecond("03:25:45");
        assertEquals(result, test);
    }

    @Test
    void formatTime() {
        String result ="03:25:45";
        String test = dateTimeHelper.formatTime(12345000L,"HH:mm:ss");
        assertEquals(result, test);
    }

    @Test
    void testFormatTime() {
        String result = "03:25";
        String test = dateTimeHelper.formatTime(12345000L);
        assertEquals(result, test);
    }

    @Test
    void testFormatDate() {
        String result = "2021-01-01";
        String test = dateTimeHelper.formatDate(1609434000000L,"yyyy-MM-dd");
        assertEquals(result, test);
    }

    @Test
    void testFormatDate1() {
        String result = "2021-01-01";
        String test = dateTimeHelper.formatDate(1609434000000L);
        System.out.println(test);
        assertEquals(result, test);
    }
}