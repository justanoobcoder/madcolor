package com.demo.service.util;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ConverterUtil {
    public static Instant convertStringToInstant(String datetime, String timezone) {
        String datetimeWithTimezone = datetime + " " + timezone;
        String pattern = "yyyy-MM-dd HH:mm:ss z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(datetimeWithTimezone, formatter);
        return zonedDateTime.toInstant();
    }

    public static Instant convertStringToInstant(String date, String time, String timezone) {
        return convertStringToInstant(date + " " + time, timezone);
    }
}
