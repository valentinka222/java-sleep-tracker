package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsTest {
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
    @DisplayName("Количество бессонных ночей равно нулю")
    void zeroSleeplessNights() {
        SleepAnalysis sleeplessNights = new SleeplessNights();
        assertEquals(0L, sleeplessNights.apply(fourSessions).result());
    }

    @Test
    @DisplayName("Одна бессонная ночь, одна сессия с 7 утра до 11 утра")
    void oneSleeplessNight() {
        SleepAnalysis sleeplessNights = new SleeplessNights();
        List<SleepingSession> oneSession = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        LocalDateTime.of(2025, 10, 3, 11, 0),
                        SleepQuality.BAD
                )
        );
        assertEquals(1L, sleeplessNights.apply(oneSession).result());
    }

    @Test
    @DisplayName("Две пропущенные ночи - две бессонные ночи, переход через месяц")
    void twoSleeplessNights() {
        SleepAnalysis sleeplessNights = new SleeplessNights();
        List<SleepingSession> twoSessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 29, 23, 59),
                        LocalDateTime.of(2025, 10, 30, 6, 0),
                        SleepQuality.BAD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 11, 2, 5, 0),
                        LocalDateTime.of(2025, 11, 2, 6, 0),
                        SleepQuality.BAD
                )
        );
        assertEquals(2L, sleeplessNights.apply(twoSessions).result());
    }

    @Test
    @DisplayName("Две сессии за одну ночь")
    void twoSleepSessionInOneNight() {
        SleepAnalysis sleeplessNights = new SleeplessNights();
        List<SleepingSession> twoSessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 30, 0, 0),
                        LocalDateTime.of(2025, 10, 30, 3, 55),
                        SleepQuality.BAD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 30, 5, 0),
                        LocalDateTime.of(2025, 10, 30, 5, 55),
                        SleepQuality.BAD
                )
        );
        assertEquals(0L, sleeplessNights.apply(twoSessions).result());
    }
}
