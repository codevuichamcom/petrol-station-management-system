package com.gasstation.managementsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
    public static long getCurrentUnixTime() {
        return new Date().getTime();
    }

    public static long toUnixTime(Date date) {
        return date.getTime() / 1000L;
    }

    public static Date toDate(long time) {
        return new Date(time * 1000);
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

}
