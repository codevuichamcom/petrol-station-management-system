package com.gasstation.managementsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {
    public static long getCurrentUnixTime() {
        return new Date().getTime();
    }

    public static long toUnixTime(Date date) {
        return date.getTime() / 1000L;
    }

    public static LocalDateTime toDateTime(long unix) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(unix), TimeZone.getDefault().toZoneId());
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

    public static String toDayMonthYearStr(long unix) {
        LocalDateTime localDateTime = toDateTime(unix);
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }
}
