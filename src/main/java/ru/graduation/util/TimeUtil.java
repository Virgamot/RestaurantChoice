package ru.graduation.util;

import ru.graduation.util.exception.TimeExpiredException;

import java.time.LocalTime;

public class TimeUtil {

    public static void checkTime(LocalTime time) {
        if (time.getHour() >= 11)
            throw new TimeExpiredException(time.toString());
    }
}
