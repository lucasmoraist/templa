package com.lucasmoraist.templa.domain.enums;

import lombok.Getter;

@Getter
public enum DayOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String value;

    DayOfWeek(String value) {
        this.value = value;
    }

}
