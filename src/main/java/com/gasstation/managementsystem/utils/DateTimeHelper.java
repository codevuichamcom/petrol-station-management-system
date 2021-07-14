package com.gasstation.managementsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DateTimeHelper {
    public static long getCurrentUnixTime() {
        return new Date().getTime();
    }

    public static long toUnixTime(Date date) {
        return date.getTime() / 1000L;
    }

    public static Date toDate(long time) {
        return new Date(time * 1000L);
    }

    public static Date toDate(String dateStr, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static long toSecond(String time) {
        LocalTime localTime = LocalTime.parse(time);
        return localTime.getHour() * 60 * 60 + localTime.getMinute() * 60 + localTime.getSecond();
    }

    public static String toHourMinuteStr(long second) {
        long hh = second / 3600;
        long mm = (second % 3600) / 60;
        return hh + ":" + mm;
    }
}
