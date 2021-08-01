package com.gasstation.managementsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {

    public static long toUnixTime(String dateTime, String format) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
        return localDateTime.atZone(TimeZone.getDefault().toZoneId()).toEpochSecond() * 1000;
    }

    public static long getCurrentUnixTime() {
        return Instant.now().toEpochMilli();
    }

    public static long getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return localDateTime.atZone(TimeZone.getDefault().toZoneId()).toEpochSecond() * 1000;
    }

    public static LocalDateTime toDateTime(long unix) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unix), TimeZone.getDefault().toZoneId());
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

    public static long toMilliSecond(String timeStr) {
        LocalTime localTime = LocalTime.parse(timeStr);
        return (localTime.getHour() * 60 * 60 + localTime.getMinute() * 60 + localTime.getSecond()) * 1000;
    }

    public static String formatTime(long milliSeconds, String format) {
        long second = milliSeconds / 1000;
        int hh = (int) (second / 3600);
        int mm = (int) ((second % 3600) / 60);
        int ss = (int) ((second % 3600) % 60);

        LocalTime localTime = LocalTime.of(hh, mm, ss);
        return DateTimeFormatter.ofPattern(format).format(localTime);
    }

    public static String formatTime(long second) {
        return formatTime(second, "HH:mm");
    }

    public static String formatDate(long unix, String format) {
        LocalDateTime localDateTime = toDateTime(unix);
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }

    public static String formatDate(long unix) {
        return formatDate(unix, "yyyy-MM-dd");
    }
}
