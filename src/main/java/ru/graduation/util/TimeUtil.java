package ru.graduation.util;

import java.time.LocalTime;

public class TimeUtil {

    public static void checkTime(LocalTime time){
        if (time.getHour()>=11)
            throw new UnsupportedOperationException("Time for this operation has passed!");
    }
}
