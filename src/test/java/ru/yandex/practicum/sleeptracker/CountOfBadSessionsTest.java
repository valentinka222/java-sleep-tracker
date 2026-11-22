package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountOfBadSessionsTest {
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
    @DisplayName("Количество плохих сессий в пустом списке равно нулю")
    void zeroBadSessionsInEmptyList() {
        SleepAnalysis countOfBadSessions = new CountOfBadSessions();
        List<SleepingSession> zeroSessions = List.of();
        assertEquals(0L,
                countOfBadSessions.apply(zeroSessions).result());
    }

    @Test
    @DisplayName("Одна плохая сессия в списке из четырех сессий")
    void oneBadSession() {
        SleepAnalysis countOfBadSessions = new CountOfBadSessions();
        assertEquals(1L,
                countOfBadSessions.apply(fourSessions).result());
    }

}
