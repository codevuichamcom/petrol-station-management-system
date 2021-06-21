package com.gasstation.managementsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
    public static long getCurrentUnixTime() {
        return new Date().getTime();
    }

    public static long toUnixTime(Date date) {
        return date.getTime();
    }

    public static Date toDate(long time) {
        return new Date(time);
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

}
