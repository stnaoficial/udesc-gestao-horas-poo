package com.udesc.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class TimeTracking extends ArrayList<LocalTime> {
    public LocalTime total() {
        return stream().reduce(LocalTime.of(0, 0),
            (total, amount) -> total.plusHours(amount.getHour())
                                    .plusMinutes(amount.getMinute()));
    }
}
