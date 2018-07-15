package com.transaction.service;


import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.MILLIS;

/**
 * Created by mumarm45 on 13/07/2018.
 */
@Service
public class TimeService {

    static final long last_mint = 60000;


    public int currentMint() {
        return (int) Duration.of(System.currentTimeMillis(), MILLIS).get(ChronoUnit.SECONDS);
    }

    public int getLastOneMint() {
        return (int) Duration.of(System.currentTimeMillis(), MILLIS).minus(1, ChronoUnit.MINUTES).get(ChronoUnit.SECONDS);
    }
    public int currentRecordMint(long timestamp) {
        return (int) Duration.of(timestamp, MILLIS).get(ChronoUnit.SECONDS);
    }




    public boolean checkTimeLessThan60(long timestamp) {

        int crntRecordTime = currentRecordMint(timestamp);
        return getLastOneMint()<= crntRecordTime && crntRecordTime<= currentMint();
    }
}
