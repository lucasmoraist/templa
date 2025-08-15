package com.lucasmoraist.templa.domain.utils;

import java.time.Duration;

public final class FormatDuration {

    public static String format(Duration duration) {
        long minutes = duration.toMinutes();
        if (minutes < 60) {
            return minutes + "min";
        } else {
            long hours = minutes / 60;
            long remainingMinutes = minutes % 60;
            if (remainingMinutes == 0) {
                return hours + "h";
            } else {
                return hours + "h " + remainingMinutes + "min";
            }
        }
    }

}
