package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinSessionDurationTest {
    private final List<SleepingSession> fourSessions = List.of(
            new SleepingSession(
                    LocalDateTime.of(2025, 10, 1, 22, 15),
                    LocalDateTime.of(2025, 10, 2, 8, 0),
                    SleepQuality.GOOD
            ),
            new SleepingSession(
                    LocalDateTime.of(2025, 10, 2, 23, 0),
                    LocalDateTime.of(2025, 10, 3, 8, 0),
                    SleepQuality.NORMAL
            ),
            new SleepingSession(
                    LocalDateTime.of(2025, 10, 3, 14, 30),
                    LocalDateTime.of(2025, 10, 3, 15, 20),
                    SleepQuality.NORMAL
            ),
            new SleepingSession(
                    LocalDateTime.of(2025, 10, 3, 23, 30),
                    LocalDateTime.of(2025, 10, 4, 6, 20),
                    SleepQuality.BAD
            )
    );

    @Test
    @DisplayName("Минимальная сессия длиной в 50 минут")
    void fiftyMinutesMinSessionDuration() {
        SleepAnalysis minSessionDuration = new MinSessionDuration();
        assertEquals(50L, minSessionDuration.apply(fourSessions).result());
    }

    @Test
    @DisplayName("Пустой список с записями о сессиях сна")
    void emptyListOfSleepLog() {
        SleepAnalysis minSessionDuration = new MinSessionDuration();
        List<SleepingSession> zeroSessions = List.of();
        assertEquals("файл с сессиями сна пуст", minSessionDuration.apply(zeroSessions).result());
    }
}
