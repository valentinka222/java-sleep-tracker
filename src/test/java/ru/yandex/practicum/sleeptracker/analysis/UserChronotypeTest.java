package ru.yandex.practicum.sleeptracker.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.model.Chronotype;
import ru.yandex.practicum.sleeptracker.model.SleepQuality;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.analysis.SleepTestData.LIST_FROM_EXAMPLE;

public class UserChronotypeTest {

    @Test
    @DisplayName("Пользователь-голубь из примера ТЗ")
    void userPigeonFromExample() {
        SleepAnalysis userChronotype = new UserChronotype();
        assertEquals(Chronotype.PIGEON, userChronotype.apply(LIST_FROM_EXAMPLE).result());
    }

    @Test
    @DisplayName("Пользователь - явная сова")
    void userNightOwl() {
        SleepAnalysis userChronotype = new UserChronotype();
        List<SleepingSession> nightOwlsNights = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 0, 15),
                        LocalDateTime.of(2025, 10, 1, 10, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 55),
                        LocalDateTime.of(2025, 10, 2, 6, 55),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 15),
                        LocalDateTime.of(2025, 10, 3, 12, 20),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 22, 30),
                        LocalDateTime.of(2025, 10, 4, 6, 20),
                        SleepQuality.BAD
                )
        );
        assertEquals(Chronotype.NIGHT_OWL, userChronotype.apply(nightOwlsNights).result());
    }

    @Test
    @DisplayName("Пользователь - явный жаворонок")
    void userEarlyBird() {
        SleepAnalysis userChronotype = new UserChronotype();
        List<SleepingSession> earlyBirdsNights = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 15),
                        LocalDateTime.of(2025, 10, 2, 6, 30),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 55),
                        LocalDateTime.of(2025, 10, 3, 10, 55),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 19, 15),
                        LocalDateTime.of(2025, 10, 4, 6, 20),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 4, 22, 30),
                        LocalDateTime.of(2025, 10, 5, 6, 20),
                        SleepQuality.BAD
                )
        );
        assertEquals(Chronotype.EARLY_BIRD, userChronotype.apply(earlyBirdsNights).result());
    }

    @Test
    @DisplayName("Ничья между совой и жаворонком: пользователь-голубь")
    void userPigeonBecauseNightOwlEqualsEarlyBird() {
        SleepAnalysis userChronotype = new UserChronotype();
        List<SleepingSession> pigeonsNights = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 15),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 0, 0),
                        LocalDateTime.of(2025, 10, 3, 12, 0),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 14, 30),
                        LocalDateTime.of(2025, 10, 3, 15, 20),
                        SleepQuality.NORMAL
                )
        );
        assertEquals(Chronotype.PIGEON, userChronotype.apply(pigeonsNights).result());
    }
}
