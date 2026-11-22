package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.model.SleepQuality;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.analysis.SleepTestData.LIST_FROM_EXAMPLE;

public class SleeplessNightsTest {

    @Test
    @DisplayName("Количество бессонных ночей равно нулю")
    void zeroSleeplessNights() {
        SleepAnalysis sleeplessNights = new SleeplessNights();
        assertEquals(0L, sleeplessNights.apply(LIST_FROM_EXAMPLE).result());
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
