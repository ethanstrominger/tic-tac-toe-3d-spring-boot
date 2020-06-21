package com.ethan.unit;

import lombok.SneakyThrows;

public class TestUtil {
    @SneakyThrows
    public static void pause(long firstSleepMillis) {
        Thread.sleep(firstSleepMillis);
    }
}
